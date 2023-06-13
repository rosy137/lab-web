package com.itwill.post.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.datasource.HikariDataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;
import com.itwill.post.model.Post;

//Repository(Persistence) Layer(저장소/영속성 계층)
//DB CRUD(Create, Read, Update, Delete) 작업
public class PostDao {
    // Slf4j 로깅 기능 사용:
    private static final Logger log = LoggerFactory.getLogger(PostDao.class);
    
    private static PostDao instance = null;
    private HikariDataSource ds;
    private PostDao() {
        ds = HikariDataSourceUtil.getInstance().getDataSource();
    }
    public static PostDao getInstance() {
        if(instance == null) {
            instance = new PostDao();
            
        }
        return instance;
    }
    
    // 저장되어있는 포스트의 내용을 가지고 옴
    private Post recordToPost(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        String content = rs.getString("CONTENT");
        String author = rs.getString("AUTHOR");
        LocalDateTime created = rs.getTimestamp("CREATED_TIME").toLocalDateTime();
        LocalDateTime modified = rs.getTimestamp("MODIFIED_TIME").toLocalDateTime();
        
        Post post = new Post(id, title, content, author, created, modified);
        return post;
    }
    
    // POSTS 테이블에서 전체 레코드를 id 내림차 순으로 정렬해서 검색.
    private static final String SQL_SELECT_ALL = 
            "select * from POSTS order by ID desc";
   
    // Post 리스트 전체 검색
    public List<Post> select(){
        List<Post> list = new ArrayList<>();
        
        log.info(SQL_SELECT_ALL);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection(); // 풀에서 connection 객체를 빌려옴
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()) {
                //테이블 컬럼 내용을 포스트 객체로 변환하고 리스트에 추가
                Post post = recordToPost(rs);
                list.add(post);
            }
            log.info("# of rows = {}", list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    
    
    // 새로운 포스트 입력을 위한 문장
    private static final String SQL_INSERT=
            "insert into POSTS (TITLE, CONTENT, AUTHOR) values (?,?,?)";

    // 새로운 포스트 입력
    public int insert(Post post) {
        log.info("insert({})", post);
        
        int result = 0; // executeUpdate() 결과를 저장할 변수
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());
            
            result = stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        return result;
    }
    
    // 포스트 id로 출력하기 위한 문장
    private static final String SQL_SELECT_ID = 
            "select * from POSTS where ID = ?"
            ;
    
    // 포스트 id 번호로 출력
    public Post detailSelect(long id) {
        Post post = null;
        log.info("select(id={})", id);
        log.info(SQL_SELECT_ID);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setLong(1,id);
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                post = recordToPost(rs);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return post;
    }
    
    private static final String SQL_DELETE_ID = 
            "delete from POSTS where ID = ?";
    public int delete(long id) {
        log.info("delete(id={})", id);
        log.info(SQL_DELETE_ID);
        // SQL 실행 결과를 저장할 변수
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_ID);
            stmt.setLong(1, id);
            result = stmt.executeUpdate();
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    private static final String SQL_UPDATE_BY_ID = 
            "update POSTS set TITLE = ? , CONTENT = ?, MODIFIED_TIME = sysdate where ID = ?";
    public int update(Post post) {
        log.info("update({})", post);
        log.info(SQL_UPDATE_BY_ID);
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_BY_ID);
            
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());
            result = stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
                
        return result;
    }
    private static final String SQL_SEARCH_TITLE =
            "select * from POSTS where TITLE like ?";
    private static final String SQL_SEARCH_CONTENT =
            "select * from POSTS where CONTENT like ?";
    private static final String SQL_SEARCH_TITLE_CONTENT =
            "select * from POSTS where TITLE like ? or CONTENT like ?";
    private static final String SQL_SEARCH_AUTHOR =
            "select * from POSTS where AUTHOR like ?";
    
    public List<Post> search(String keyword, String value) {
        List<Post> list = new ArrayList<>();
        log.info("search({})", keyword);
        log.info(SQL_SEARCH_TITLE);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            switch(value) {
            case "t": 
                stmt = conn.prepareStatement(SQL_SEARCH_TITLE);
                stmt.setString(1, "%" + keyword + "%");
                break;
            case "c":
                stmt = conn.prepareStatement(SQL_SEARCH_CONTENT);
                stmt.setString(1, "%" + keyword + "%");
                break;
            case "tc": 
                stmt = conn.prepareStatement(SQL_SEARCH_TITLE_CONTENT);
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                break;
            case "a":
                stmt = conn.prepareStatement(SQL_SEARCH_AUTHOR);
                stmt.setString(1, "%" + keyword + "%");
                break;
            }
            
            rs = stmt.executeQuery();
            while(rs.next()) {
                Post post = recordToPost(rs);
                list.add(post);
            }
            log.info("# of rows = {}", list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }

    
}

package com.itwill.post.jdbc;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.itwill.post.model.Post;

import oracle.jdbc.OracleDriver;

import static com.itwill.post.model.Post.Entity.*;

// JUnit Test(자바 단위 테스트)를 하기 위한 클래스.
// JDBC(Java Database Connectivity) 테스트 - ojdbc11 라이브러리 동작 여부 테스트.
// main 메서드를 만들지 않고, 테스트 메서드를 작성하면,
// junit-jupiter-engine에서 테스트 메서드를 실행함.
@TestMethodOrder(OrderAnnotation.class)
public class JdbcTest {

    // Oracle 데이터베이스 접속 주소
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    // 데이터베이스 접속 계정
    private static final String USER = "scott";
    // 데이터베이스 접속 비밀번호
    private static final String PASSWORD = "tiger";
    
    // 테스트 메서드 작성:
    //  (0) @Test 애너테이션 사용.
    //  (1) 가시성: public     (2) 리턴타입: void      (3) 파라미터를 갖지 않음.
    //  테스트 성공/실패 여부는 테스트 메서드 작성자가 설정.
    
    @Test  // 테스트 메서드 
    @Order(2) // 2번째로 실행할 테스트 메서드
    public void testSelect() throws SQLException {
        // 1. 라이브러리를 DriverManager에 등록.
        DriverManager.registerDriver(new OracleDriver());
        System.out.println("JDBC 드라이버 등록 성공");
        
        // 2. 등록된 JDBC 드라이버를 사용해서 데이터베이스 서버에 접속.
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        
        Assertions.assertNotNull(conn);
        //-> conn이 null이 아니면 테스트 성공, 그렇지 않으면 테스트 실패.
        System.out.println("conn = " + conn);
        
        // POSTS 테이블의 전체 내용을 검색하고 그 결과를 콘솔창에 출력.
        // ArrayList<Post>의 원소갯수는 1임을 주장 Asso
        String sql = String.format("select * from %s", TBL_NAME);
        System.out.println(sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        System.out.println("stmt = " + stmt);
        ResultSet rs = stmt.executeQuery();
        System.out.println("rs = " + rs);
        List<Post> list = new ArrayList<>();
        
        while(rs.next()) {
            Long id = rs.getLong(COL_ID);
            String title = rs.getString(COL_TITLE);
            String content = rs.getString(COL_CONTENT);
            String author = rs.getString(COL_AUTHOR);
            LocalDateTime createdTime = rs.getTimestamp(COL_CREATEDTIME).toLocalDateTime();
            LocalDateTime modifiedTime = rs.getTimestamp(COL_MODIFIEDTIME).toLocalDateTime();
            
            Post post = new Post(id, title, content, author, createdTime, modifiedTime);
            
            list.add(post);
            System.out.println(post);
        }
        
        Assertions.assertEquals(3, list.size());
        
        // 사용했던 리소스 해제
        // 데이터베이스와 연결된 접속을 해제.
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("연결 해제 성공.");
    }
    
    @Test // jUnit 엔진에서 호출할 테스트 메서드
    @Order(1) // 첫번째로 실행할 테스트 메서드
    public void testInsert() throws SQLException {
        //Driver 등록 > connection > ps >execut > result> 리소스 해제
        Scanner scan = new Scanner(System.in);
        DriverManager.registerDriver(new OracleDriver());
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //INSERT INTO posts (title,content,author)VALUES ('test','테스트','admin');
        String sql = String.format("insert into %s ( %s, %s, %s) values (?, ?, ?)", TBL_NAME, COL_TITLE, COL_CONTENT,COL_AUTHOR);
        System.out.println("sql = " + sql);
        PreparedStatement stmt = conn.prepareStatement(sql);
        System.out.println("제목");
        String title = scan.nextLine();
        System.out.println("내용");
        String content = scan.nextLine();
        System.out.println("작성자");
        String author = scan.nextLine();
        
        stmt.setString(1, title);
        stmt.setString(2, content);
        stmt.setString(3, author);
        
        int result = stmt.executeUpdate();
        Assertions.assertEquals(1, result);
        //-> insert 문장의 실행 결과가 1이면 단위 테스트 성공, 그렇지 않으면 실패.
        
        stmt.close();
        conn.close();
    }
}

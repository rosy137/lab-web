package com.itwill.spring3.repository.post;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.spring3.dto.PostUpdateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    
    //@Test
    public void testFindAll() {
        List<Post> list = postRepository.findByOrderByIdDesc();
        for(Post p: list) {
            log.info(p.toString());
        }
    }
    
    //@Test
    public void testFindById() {
        Post post = postRepository.findById(61);
        log.info(post.toString());
    }
    
    //@Test
    public void testSave() {
        //DB 테이블에 insert할 레코드(엔터티)를 생성:
        Post entity = Post.builder()
                .title("JPA test for update")
                .content("insert with JPA library for update test")
                .author("admin")
                .build();
        log.info("before insert: {}", entity);
        log.info("created: {},. modified: {}", entity.getCreatedTime(), entity.getModifiedTime());;
        
        //DB 테이블에 insert:
        postRepository.save(entity);
        //-> save 메서드는 테이블에 삽입할 엔터티를 파라미터에 전달하면,
        //   테이블에 저장된 엔터티 객체를 리턴
        //-> 파라미터에 전달된 엔테티 필드들을 변경해서 리턴
        
        log.info("after insert: {}", entity);
        log.info("created: {},. modified: {}", entity.getCreatedTime(), entity.getModifiedTime());;
        
    }
    
    @Test
    public void testUpdate() {
        Post entity = postRepository.findById(43L).orElseThrow();
        // 'findById'의 리턴값은 Optional<Post> 
        // > 결과가 있으면 Post, 없으면 error
        // >> orElseThrow로 오류 잡아줌
        log.info("before update: {}", entity);
        log.info("before update modified time: {}", entity.getModifiedTime());
        
        // 엔터티를 변경할 내용을 가지고 있는 객체 생성:
        PostUpdateDto dto = new PostUpdateDto();
        dto.setTitle("JPA update test");
        dto.setContent("JPA Hibernate DB update");
        
        // 엔터티 수정:
        entity.update(dto);
        
        // DB 테이블 업데이트:
        // JPA에서는 insert와 update 메서드가 구분되어 있지 않음.
        // save() 메서드의 아규먼트가 DB에 없는 엔터티이면 insert, DB에 없는 엔터티이면 update를 실행.
        postRepository.saveAndFlush(entity);
        
        entity = postRepository.findById(43L).orElseThrow();
        log.info("after update: {}", entity);
        log.info("after update modified time: {}", entity.getModifiedTime());
        
    }
    
    //@Test
    public void testDelete() {
        long count = postRepository.count(); // DB 테이블의 행의 개수(엔터티 개수)
        log.info("삭제 전 count = {}", count);
        
        postRepository.deleteById(21L); // (외래키로 사용중이면) 댓글 있으면 삭제 안됨
        count = postRepository.count();
        log.info("삭제 후 count = {}", count);
        
    }
}

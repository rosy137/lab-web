package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
        )

public class RepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Test
    public void testDelete() {
        int result = postRepository.deleteById(43);
        Assertions.assertEquals(1, result);
        log.info("resultOfTestDelete= {}", result);
    }
    
    //@Test
    public void testUpdate() {
        Post post = Post.builder()
                .id(1)
                .title("업데이트 test")
                .content("MyBatisFramework를 사용한 DB업데이트")
                .build();
                
        int result = postRepository.updateTitleAndContent(post);
        Assertions.assertEquals(1, result);
        log.info("resultofTestUpdate= {}", result);
    }
    
    // @Test
    public void testSelectByid() {
       Post result = postRepository.selectById(1);
       Assertions.assertNotNull(result);
       log.info(result.toString());
    }
    
    // @Test
    public void testSelectOrderByIdDesc() {
        List<Post> list = postRepository.selectOrderByIdDesc();
        for(Post p: list) {
            log.info(p.toString());
        }
    }
    // @Test
    public void testPostRepository() {
        Assertions.assertNotNull(postRepository);
        log.info("postRepository = {}", postRepository);
        
        Post post = Post.builder()
                .title("MyBatis test")
                .content("MyBatis를 이용한 insert")
                .author("admin")
                .build();
        log.info(post.toString());
        
        int result = postRepository.insert(post);
        Assertions.assertEquals(1, result);
        log.info("result = {}", result);
    }
   
}

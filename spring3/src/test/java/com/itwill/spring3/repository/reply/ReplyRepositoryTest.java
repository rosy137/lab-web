package com.itwill.spring3.repository.reply;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.post.PostRepository;

import jakarta.persistence.FetchType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReplyRepositoryTest {
    
    @Autowired
    private ReplyRepository replyRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    //@Test
    public void testFindById() {
        // 댓글 번호로 검색하기
        Reply reply = replyRepository.findById(3L).orElseThrow();
        
        log.info(reply.toString());
        //log.info(reply.getPost().toString());
        
        // findById() 메서드는
        // Reply 엔터티에서 FetchType.EAGER를 사용한 경우 join 문장 실행, 
        // FetchType.LAZY를 사용한 경우에는 단순 select 실행, 
        // 필요시 join 실행
    }
    
    @Test
    public void testFindByPost() {
        Post post = postRepository.findById(1L).orElseThrow();
        List<Reply> list = replyRepository.findByPostOrderByIdDesc(post);
        for(Reply r: list) {
            log.info(r.toString());
        }
    }
}

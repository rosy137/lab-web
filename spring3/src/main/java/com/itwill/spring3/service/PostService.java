package com.itwill.spring3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.post.PostCreateDto;
import com.itwill.spring3.dto.post.PostSearchDto;
import com.itwill.spring3.dto.post.PostUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.post.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    
    // 생성자를 사용한 의존성 주입:
    private final PostRepository postRepository;
    
    // DB POST 테이블에서 전체 검색한 결과를 리턴
    public List<Post> read() {
        log.info("read()");
        return postRepository.findByOrderByIdDesc();
    }
    
    // DB에 있는 POSTS 테이블에 새 entity를 insert:
    public Post create(PostCreateDto dto) {
        log.info("create(dto={}",dto);
        
        // DTO를 entity로 변환:
        Post entity = dto.toEntity();
        log.info("entity= {}", entity);
        
        // DB 테이블에 저장
        postRepository.save(entity);
        log.info("entity= {}", entity);
        
        return entity;
    }

    public Post read(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Post update(PostUpdateDto dto) {
    	log.info("update({})", dto);
    	
    	// (1) 메서드에 @Transactional 애너테이션을 설정 
    	// (2) DB에서 엔터티를 검색 
    	// (3) 검색한 엔터티를 수정
    	// 트랜잭션이 끝나는 시점에 DB 업데이트가 자동으로 수행
    	
    	//Post entity = postRepository.findById(id).orElseThrow();
    	//entity.update(dto);
    	
    	Post entity = postRepository.findById(dto.getId()).orElseThrow();
    	entity.update(dto);
    	return postRepository.saveAndFlush(entity);
    }
    
	public void delete(long id) {
		log.info("delete(id={})",id);
		postRepository.deleteById(id);
	}
    
	@Transactional(readOnly = true)
	public List<Post> search(PostSearchDto dto){
        List<Post> list = null;
        switch(dto.getType()) {
        case "t" :
            list = postRepository.findByTitleContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        case "c" :
            list =  postRepository.findByContentContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        case "tc" :
            list =  postRepository.findByTitleContainsIgnoreCaseOrContentContainsIgnoreCaseOrderByIdDesc(dto.getKeyword(), dto.getKeyword());
            break;
        case "a" :
            list =  postRepository.findByAuthorContainsIgnoreCaseOrderByIdDesc(dto.getKeyword());
            break;
        }
        return list;
    }
    
}

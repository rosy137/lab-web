package com.itwill.spring3.web;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    
    @GetMapping
    public String read(Model model) {
        log.info("read()");
        
        List<Post> list = postService.read();
        model.addAttribute("posts", list);
        
        return "/post/read";
    }
    
     @GetMapping("/create")
     public void create() {
         log.info("create() GET");
     }
    
     @PostMapping("/create")
     public String create(PostCreateDto dto) {
         log.info("create(dto={}) POST", dto);
         
         postService.create(dto);
         // DB 테이블 insert 후 포스트 목록 페이지로 이동.
         return "redirect:/post";
     }
     
     // "/details", "/modify" 요청 주소를 처리하는 컨트롤러 메서드
     @GetMapping({"/details", "/modify"})
     public void read(Long id, Model model) {
         log.info("read(id={})", id);
         
         // POSTS 테이블에서 ID에 해당하는 포스트를 검색.
         Post post = postService.read(id);
         
         // 결과를 model에 저장.
         model.addAttribute("post", post);
         
         // 컨트롤러 메서드의 리턴값이 없는 경우(void인 경우),
         // 뷰의 이름은 요청 주소와 같다!
         // details -> details.html, modify -> modify.html
         
     }
     
     
     
}

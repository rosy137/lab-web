package com.itwill.spring3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PostController {

    @GetMapping("/post")
    public String read() {
        log.info("read()");
        
        //TODO: 포스트 목록 검색
        return "/post/read";
    }
    
     @GetMapping("/create")
     public void create() {
         log.info("create() GET");
     }
    
}

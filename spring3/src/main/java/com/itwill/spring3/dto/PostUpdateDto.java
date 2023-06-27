package com.itwill.spring3.dto;

import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
public class PostUpdateDto {

    private String title;
    private String content;
}

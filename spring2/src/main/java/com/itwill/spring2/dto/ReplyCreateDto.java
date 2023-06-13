package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Reply;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class ReplyCreateDto {

    private long postId; // 댓글이 달린 포스트 아이디
    private String replyText; // 댓글 내용
    private String writer; // 댓글 작성자 아이디
    
    //ReplyCreateDto 타입의 객체를 Reply 타입으로 변환해서 리턴하는 메서드 
    public Reply toEntity() {
        return Reply.builder()
                .post_id(postId)
                .reply_text(replyText)
                .writer(writer)
                .build();
       
    }
}

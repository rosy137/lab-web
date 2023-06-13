package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// 댓글 테이블(REPLIES)에 저장되는 레코드

@Data
@Builder
public class Reply {
      private long id; //pk 댓글 아이디
      private long post_id; // 댓글 달린 포스트 아이디
      private String reply_text; // 댓글 내용
      private String writer; // 작성자
      private LocalDateTime created_time; //작성 시간
      private LocalDateTime modified_time; // 수정 시간
}

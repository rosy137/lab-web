package com.itwill.spring3.repository.reply;

import com.itwill.spring3.repository.BaseTimeEntity;
import com.itwill.spring3.repository.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"post"} )
@Entity
@Table(name = "REPLIES")
@SequenceGenerator(name = "REPLIES_SEQ_GEN", sequenceName = "REPLIES_SEQ", allocationSize = 1 )
public class Reply extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLIES_SEQ_GEN")
    private Long id; // Primary Key
    
    @ManyToOne(fetch = FetchType.LAZY) 
    // EAGER(기본값): 즉시 로딩(join 문장 실행), LAZY: 지연 로딩(나중에 필요한 경우 join 문장 실행)
    private Post post; // Foreign Key, 관계를 맺고 있는 엔터티
    
    @Column(nullable = false)
    private String replyText; // 댓 내용
    
    @Column(nullable = false)
    private String writer; // 댓 작성자
}

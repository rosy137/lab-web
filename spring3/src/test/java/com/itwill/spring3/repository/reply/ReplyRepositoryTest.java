package com.itwill.spring3.repository.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ReplyRepositoryTest {
    
    @Autowired
    private ReplyRepository replyRepository;
}

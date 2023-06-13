package com.itwill.post.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.post.model.User;

public class UserDaoTest {
    private final Logger log = LoggerFactory.getLogger(UserDaoTest.class);
    
    @Test
    public void loginTest() {
        UserDao userDao = UserDao.getInstance();
        Assertions.assertNotNull(userDao);
        
        // username = admin, password = admin0000인 테스트 사용자 객체
        User test1 = new User(0, "admin", "admin0000", null, 0);
        User result = userDao.selectByUserNameAndPassword(test1);
        
        // username과 password가 일치하는 사용자를 찾을 수 있어야 함.
        Assertions.assertNotNull(result);
        log.info("result: {}", result);
        
        // username과 password가 일치하지 않는 사용자를 찾을 수 없어야 함.
        User test2 = new User(0, "Admin", "admin0000", null, 0);
        result = userDao.selectByUserNameAndPassword(test2);
        Assertions.assertNull(result);
        log.info("result: {}", result);
    }
}

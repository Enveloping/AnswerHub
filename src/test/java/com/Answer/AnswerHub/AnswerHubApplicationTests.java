package com.Answer.AnswerHub;

import com.Answer.AnswerHub.constants.AnswerHubConstants;
import com.Answer.AnswerHub.utils.GetUserInfoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class AnswerHubApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test(){

    }
}

package com.Answer.AnswerHub;

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
        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println(redisTemplate.opsForValue().get("zero"));
        System.out.println(redisTemplate.opsForValue().get("one"));
    }
}

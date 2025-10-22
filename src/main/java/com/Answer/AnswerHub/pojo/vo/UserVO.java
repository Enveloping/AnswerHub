package com.Answer.AnswerHub.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /**
     * 用户名
     */
    String userName;
    /**
     * 用户类型
     */
    String userType;
    /**
     * token
     */
    String token;

}

package com.Answer.AnswerHub.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserDo {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    Long userId;
    /**
     * 用户名
     */
    String userName;
    /**
     * 用户类型
     */
    String userType;
    /**
     * 用户手机号
     */
    String userPhone;
    /**
     * openId
     */
    String openId;



}

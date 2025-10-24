package com.Answer.AnswerHub.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;


public class GetUserInfoUtil {

    /**
     * 从SecurityContext中获取用户信息
     * @return 用户信息的Map
     */
    public static Map<String, String> getUserInfo() {
        // 从SecurityContext中获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // 获取用户主体（principal）
            Object principal = authentication.getPrincipal();
            // 判断principal是否为Map类型

            return (Map<String, String>) principal;
        }
        // 如果没有认证信息或principal不是Map类型，返回null或抛出异常
        return null;

    }
}

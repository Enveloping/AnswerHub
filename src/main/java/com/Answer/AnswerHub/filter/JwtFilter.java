package com.Answer.AnswerHub.filter;

import com.Answer.AnswerHub.constants.AnswerHubConstants;
import com.Answer.AnswerHub.properties.JwtProperties;
import com.Answer.AnswerHub.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain chain) throws ServletException, IOException {
        try {
            String token = request.getHeader(jwtProperties.getTokenName());
            if (!Objects.isNull(token) && !token.isBlank()) {
                Claims claims = Objects.requireNonNull(JwtUtil.parseJWT(jwtProperties.getSecretKey(), token));
                String openid = claims.get(AnswerHubConstants.WX_LOGIN.OPEN_ID).toString();//获取openid
                String userType = claims.get(AnswerHubConstants.WX_LOGIN.USER_TYPE).toString();//获取用户类型
                if (validateUserType(userType)) {
                    log.error("非法的用户类型 : {}",userType);
                }
                Map<String,String> userInfo = Map.of(AnswerHubConstants.WX_LOGIN.OPEN_ID,openid,AnswerHubConstants.WX_LOGIN.USER_TYPE,userType);
                //三个参数表示创建已认证的用户，用户标识，用户凭证（已认证，所以传null），用户权限
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userInfo, null, List.of(new SimpleGrantedAuthority(userType)));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);   // 继续往后走,如果token异常则不会认证，后面的filter会拦截，所以这里直接往后走。
        } finally {
            SecurityContextHolder.clearContext(); // 在调用完业务逻辑后，清理上下文
        }
    }

    private boolean validateUserType(String userType){
        return userType.equals(AnswerHubConstants.AUTHORITY.TEACHER) ||
                userType.equals(AnswerHubConstants.AUTHORITY.STUDENT) ||
                userType.equals(AnswerHubConstants.AUTHORITY.ADMIN);
    }
}

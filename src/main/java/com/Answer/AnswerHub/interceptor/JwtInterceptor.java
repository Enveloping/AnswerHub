//package com.Answer.AnswerHub.interceptor;
//
//
//import com.Answer.AnswerHub.context.BaseContext;
//import com.Answer.AnswerHub.properties.JwtProperties;
//import com.Answer.AnswerHub.utils.JwtUtil;
//import io.jsonwebtoken.Claims;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Arrays;
//import java.util.Map;
//
////@Slf4j
//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//  @Resource
//  JwtProperties jwtProperties;
//
//  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtInterceptor.class);
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    Map<String, String[]> parameterMap = request.getParameterMap();
//    parameterMap.forEach((key,value)->{
//      System.out.println(key +" : "+ Arrays.toString(value));
//    });
//
//    log.info("开始校验token");
//    String token = request.getHeader(jwtProperties.getTokenName());
//    //token为空，直接拒绝
//    if(ObjectUtils.isEmpty(token) || token.isEmpty()){
//      log.warn("token为空!");
//      response.setStatus(401);
//      return false;
//    }
//    try{
//      //解析token，得到userId
//      Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(),token);
//      int userId = Integer.parseInt(claims.get("userId").toString());
//      //设置应用上下文
//      BaseContext.setCurrentUserId(userId);
//      log.info("获取到userId:"+userId);
//    }catch (Exception e){
//      log.error(e.getMessage());
//      response.setStatus(401);
//      return false;
//    }
//    return true;
//  }
//
//  @Override
//  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//  }
//
//  @Override
//  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    BaseContext.remove();
//  }
//}

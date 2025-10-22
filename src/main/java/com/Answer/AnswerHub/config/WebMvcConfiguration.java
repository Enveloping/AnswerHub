package com.Answer.AnswerHub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {//扩展Spring MVC 的默认行为。

//  @Resource
//  private JwtInterceptor jwtInterceptor;
//
//  /**
//   * 注册JwtToken拦截器
//   * @param registry 传入的一个注册器，可以通过它注册拦截器
//   */
//
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(jwtInterceptor)
//        .addPathPatterns("/**")//拦截所有请求
//        .excludePathPatterns("/user/login")//只放行了登录接口
//        .order(0);//值越小,越先执行
//        //如果要从后端获取静态资源，需要哦额外放行"/static/**"
//  }

  /**
   * 数据转换器（暂未配置）
   * @param converters 传入的转换器列表，可以往里面加自定义的转换器
   */
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    WebMvcConfigurer.super.configureMessageConverters(converters);
  }


}

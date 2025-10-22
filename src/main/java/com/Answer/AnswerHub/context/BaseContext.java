package com.Answer.AnswerHub.context;

/**
 * 应用上下文容器(记录当前用户信息)
 */
public class BaseContext {
  public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
  public static void setCurrentUserId(Integer userId){threadLocal.set(userId);}
  public static Integer getCurrentUserId(){return threadLocal.get();}
  public static void remove(){threadLocal.remove();}
}

package com.Answer.AnswerHub.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {
  /**
   *       value = "错误码",
   *       dataType = "String",
   *       example = "0",
   */
  private String code;
  /**
   *       value = "错误码",
   *       dataType = "HttpStatus",
   *       example = "HttpStatus.OK",
   */
  private HttpStatus states;
  /**
   *       value = "请求头",
   *       dataType = "HttpHeaders",
   *       example = "HttpStatus.OK",
   *       headers.setContentType(MediaType.parseMediaType("audio/mp4"));
   */
  private HttpHeaders headers;
  /**
   *       value = "返回描述信息",
   *       dataType = "String",
   *       example = "SUCCESS",
   */
  private String msg;
  /**
   *       value = "返回数据封装",
   */
  private T data;
  public BaseResult(HttpStatus states) {
    this.code = String.valueOf(states.value());
    this.msg = states.getReasonPhrase();
  }

  public BaseResult(HttpStatus states, T data) {
    this.code = String.valueOf(states.value());
    this.msg = states.getReasonPhrase();
    this.data = data;
  }
  public BaseResult(T data, HttpStatus states) {
    this.code = String.valueOf(states.value());
    this.msg = states.getReasonPhrase();
    this.data = data;
    this.states = states;
  }
  public BaseResult(T data, HttpHeaders headers, HttpStatus states) {
    this.code = String.valueOf(states.value());
    this.msg = states.getReasonPhrase();
    this.states = states;
    this.data = data;
    this.headers = headers;
  }
  public BaseResult(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public BaseResult(String code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public static <T> BaseResult success(T t) {
    return new BaseResult("200", "SUCCESS", t);
  }

  public static <T> BaseResult<T> fail(String code, String message) {
    return new BaseResult(code, message);
  }

  public static <T> BaseResult<T> error(String code, String message) {
    return new BaseResult(code, message);
  }


}

package com.Answer.AnswerHub.exception;

import com.Answer.AnswerHub.common.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public BaseResult<?> handleRuntimeException(RuntimeException e) {
        log.error(e.toString(), e);
        return BaseResult.error("500","服务器异常,该干活了:(");
    }
}

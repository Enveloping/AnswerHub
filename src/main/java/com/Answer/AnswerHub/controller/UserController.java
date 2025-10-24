package com.Answer.AnswerHub.controller;

import com.Answer.AnswerHub.common.BaseResult;
import com.Answer.AnswerHub.constants.AnswerHubConstants;
import com.Answer.AnswerHub.pojo.dto.UserDTO;
import com.Answer.AnswerHub.pojo.vo.UserVO;
import com.Answer.AnswerHub.service.UserService;
import com.Answer.AnswerHub.utils.GetUserInfoUtil;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.baomidou.mybatisplus.extension.ddl.DdlScriptErrorHandler.PrintlnLogErrorHandler.log;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public BaseResult<?> login(@RequestBody UserDTO user) throws IOException {
        UserVO userVo = userService.login(user);

        return BaseResult.success(userVo);

    }
}

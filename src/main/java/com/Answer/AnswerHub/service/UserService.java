package com.Answer.AnswerHub.service;

import com.Answer.AnswerHub.pojo.dto.UserDTO;
import com.Answer.AnswerHub.pojo.vo.UserVO;

import java.io.IOException;

public interface UserService {
    public UserVO login(UserDTO user) throws IOException;
}

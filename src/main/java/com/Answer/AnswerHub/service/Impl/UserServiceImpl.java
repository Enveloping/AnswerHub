package com.Answer.AnswerHub.service.Impl;

import com.Answer.AnswerHub.constants.AnswerHubConstants;
import com.Answer.AnswerHub.exception.error.LoginException;
import com.Answer.AnswerHub.mapper.UserMapper;
import com.Answer.AnswerHub.pojo.dto.UserDTO;
import com.Answer.AnswerHub.pojo.entity.UserDo;
import com.Answer.AnswerHub.pojo.vo.UserVO;
import com.Answer.AnswerHub.properties.JwtProperties;
import com.Answer.AnswerHub.properties.WxLoginProperties;
import com.Answer.AnswerHub.service.UserService;
import com.Answer.AnswerHub.utils.HttpUtil;
import com.Answer.AnswerHub.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    WxLoginProperties wxLoginProperties;
    @Resource
    UserMapper userMapper;
    @Resource
    JwtProperties jwtProperties;

    @Override
    public UserVO login(UserDTO user) throws IOException {
        String openId = getOpenId(user.getCode());
        UserDo userDo = userMapper.selectOne(new LambdaQueryWrapper<UserDo>().eq(UserDo::getOpenId, openId));
        if(userDo == null){
            UserDo newUser = new UserDo(0L,user.getUserName(),user.getUserType(),null,openId);
            userMapper.insert(newUser);
            userDo = newUser;
        }
        if(Objects.equals(user.getUserName(), userDo.getUserName())){//更新用户名
            userDo.setUserName(user.getUserName());
            userMapper.update(userDo, new LambdaQueryWrapper<UserDo>().eq(UserDo::getOpenId,openId));
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put(AnswerHubConstants.WX_LOGIN.OPEN_ID, openId);
        claims.put(AnswerHubConstants.WX_LOGIN.USER_TYPE,userDo.getUserType());
        String token = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        UserVO userVo = new UserVO();
        BeanUtils.copyProperties(userDo,userVo);
        userVo.setToken(token);
        return userVo;
    }

    public String getOpenId(String code) throws IOException {
        //构建请求参数
        Map<String, Object> map = new HashMap<>();
        map.put(AnswerHubConstants.WX_LOGIN.APP_ID, wxLoginProperties.getAppId());
        map.put(AnswerHubConstants.WX_LOGIN.APP_SECRET,wxLoginProperties.getAppSecret());
        map.put(AnswerHubConstants.WX_LOGIN.JS_CODE,code);
        map.put(AnswerHubConstants.WX_LOGIN.GRANT_TYPE, AnswerHubConstants.WX_LOGIN.GRANT_TYPE_VALUE);
        String jsonString = HttpUtil.doGet(wxLoginProperties.getServerAddress(),map);
        ObjectMapper mapper = new ObjectMapper();
        String openId = null;
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            openId = jsonNode.get(AnswerHubConstants.WX_LOGIN.OPEN_ID).asText();
        } catch (JsonProcessingException e) {
            throw new LoginException("获取 openid 异常");
        }
        return openId;
    }
}

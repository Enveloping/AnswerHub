package com.Answer.AnswerHub.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * 微信登录属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
@PropertySource("classpath:config.properties")
public class WxLoginProperties {

    private String appId;

    private String appSecret;

    private String serverAddress;

    @Value("${wx.appId}")
    public void setAppId(String str) {
        this.appId = str;
    }

    @Value("${wx.appSecret}")
    public void setAppSecret(String str) {
        this.appSecret = str;
    }

    @Value("${wx.server}")
    public void setServerAddress(String str) {
        this.serverAddress = str;
    }


}



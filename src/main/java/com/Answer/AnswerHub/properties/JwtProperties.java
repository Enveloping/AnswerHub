package com.Answer.AnswerHub.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * jwt相关配置，一般从这类里面取出配置属性
 */
@Getter
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:config.properties")
public class JwtProperties {
  private String secretKey;
  private Long ttl;
  private String tokenName;

  @Value("${jwt.token.secret.key}")
  public void setSecretKey(String secretKey){this.secretKey = secretKey;}
  @Value("${jwt.token.ttl}")
  public void setTtl(Long ttl){this.ttl = ttl;}
  @Value("${jwt.token.name}")
  public void setTokenName(String tokenName){this.tokenName = tokenName;}

}

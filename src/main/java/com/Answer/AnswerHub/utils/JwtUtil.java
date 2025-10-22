package com.Answer.AnswerHub.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

  /**
   * 创建 JwtToken
   * @param secretKey 私钥
   * @param ttl token寿命
   * @param claims 载荷，包含标识用户信息的数据或其他信息
   *               前端传过来包含token的请求，需要知道请求的用户是谁
   * @return 返回token
   */
  public static String createJWT( String secretKey, long ttl, Map<String,Object> claims){
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long exp = System.currentTimeMillis() + ttl;
    Date expire = new Date(exp);
    JwtBuilder builder = Jwts.builder()
        .setClaims(claims)
        .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
        .setExpiration(expire);
    return builder.compact();
  }

  /**
   * 解析token
   * 签名对不上 -> SignatureException
   * token 过期 -> ExpiredJwtException
   * token 格式不对 -> MalformedJwtException
   * 其他非法 -> JwtException / IllegalArgumentException
   * @param secretKey 密钥
   * @param token token
   * @return  可直接使用get方法取出里面的值
   */
  public static Claims parseJWT(String secretKey,String token){
    log.info("开始解析token:"+token);
    if (token != null && token.startsWith("Bearer ")){
      token = token.substring(7);
    }
    try {
      return Jwts.parser()
          .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
          .parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      log.error("Token已过期", e);
    } catch (MalformedJwtException e) {
      log.error("Token格式错误", e);
    } catch (SignatureException e) {
      log.error("签名验证失败", e);
    } catch (Exception e) {
      log.error("其他解析错误", e);
    }
    return null;
  }
}

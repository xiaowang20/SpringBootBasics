package com.wg.basics.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 * 注：1.JWT token的格式：header.payload.signature
 *      2.header中放入算法
 *      3.payload中用于存放用户名、token的生成时间和过期时间
 *      4.signature为以header和payload生成的签名，一旦header和payload被篡改，验证将失败
 */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成token，制定规则
     * @param claims
     * @return
     */
    public String generateToken(Map<String,Object> claims){
         return Jwts.builder()
                .setClaims(claims)//将 JWT 负载设置为由指定名称值对填充的 JSON 声明实例。
                .setExpiration(generateExpiration())//过期时间
                .signWith(SignatureAlgorithm.HS512,secret)//使用指定的算法和指定的密钥对构造的 JWT 进行签名，生成 JWS.
                 .compact();//返回n一个紧凑的 URL 安全 JWT 字符串。
}
    /**
     * 指定声明
     * @param userDetails
     * @return
     */
    public String generateTokenByUsername(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);//返回token
    }

    /**
     * 指定token过期时间
     * @return
     */
    public Date generateExpiration(){
      return new Date(System.currentTimeMillis() + expiration*1000);
    }

    /**
     *  根据token获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimKeyFromToken(token);
             username = claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }

    /**
     * 通过token得到claims实例
     * @param token
     * @return
     */
    public Claims getClaimKeyFromToken(String token){
        Claims claims =null;

        try {
            claims = Jwts.parser()//可以配置然后用于解析 JWT 字符串.
                    .setSigningKey(secret)//设置用于验证任何发现的 JWS 数字签名的签名密钥.
                    .parseClaimsJws(token)//根据构建器的当前配置状态解析指定的紧凑序列化 JWS 字符串并返回生成的 Claims JWS 实例。
                    .getBody();//返回 JWT 正文，可以是 {@code String} 或 {@code Claims} 实例。
        } catch (Exception e) {
           LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());//测试此日期是否早于指定日期
    }
    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimKeyFromToken(token);
        return claims.getExpiration();
    }
}

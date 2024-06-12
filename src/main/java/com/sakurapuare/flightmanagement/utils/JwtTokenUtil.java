package com.sakurapuare.flightmanagement.utils;

import com.sakurapuare.flightmanagement.config.JWTConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private static JWTConfig jwtConfig;

    @Autowired
    public JwtTokenUtil(JWTConfig jwtConfig) {
        JwtTokenUtil.jwtConfig = jwtConfig;
    }

    public static String createToken(String signKey, long expire, Map<String, Object> claims) {
        return Jwts.builder().addClaims(claims) // 自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, signKey) // 签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + expire)) // 过期时间
                .compact();
    }

    public static String createToken(Map<String, Object> claims) {
        return createToken(jwtConfig.getSignKey(), jwtConfig.getExpire(), claims);
    }

    public static Claims parseToken(String signKey, String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }
        return Jwts.parser().setSigningKey(signKey) // 指定签名密钥
                .parseClaimsJws(jwt) // 指定令牌Token
                .getBody();
    }

    public static Claims parseToken(String jwt) {
        return parseToken(jwtConfig.getSignKey(), jwt);
    }
}
package com.eziozhao.leafblog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eziozhao
 * @date 2020/6/1
 */
public class JwtUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    private static final String CLAIM_USERNAME = "sub";
    private static final String CLAIM_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_CREATED,new Date());
        return generateToken(claims);
    }

    private Claims getClaims(String token){
        Claims claims = null;
        try{
            claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            LOGGER.info("jwt格式验证失败 {}",token);
        }
        return claims;
    }


    public String getUsername(String token){
        String username;
        try {
            Claims claims = getClaims(token);
            username =  claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    /**
     * 验证是否过期
     */
    private boolean isExpired(String token){
        Claims claims = getClaims(token);
        Date expiredTime = claims.getExpiration();
        return expiredTime.before(new Date());
    }
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public String refreshToken(String token){
        Claims claims = getClaims(token);
        if(!isExpired(token)){
            claims.put(CLAIM_CREATED, new Date());
            return generateToken(claims);
        }else{
            LOGGER.info("token已失效");
            return null;
        }
    }
}

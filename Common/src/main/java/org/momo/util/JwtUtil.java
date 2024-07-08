package org.momo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.momo.exception.handler.JwtExpiredHandler;
import org.momo.exception.handler.JwtInvalidHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil implements InitializingBean {
    @Value("${JWT_SECRET}")
    private String secret;
    private static SecretKey secretKey;
    private static final Long expireMs = 1000L * 60; //30분
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public static String getEmail(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

    public static String getRole(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public static Boolean isExpired(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public static String getPassword(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("password", String.class);
    }

    public static String createJwt(Long memberId, String email, String role){
        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("email", email)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expireMs))
                .signWith(secretKey)
                .compact();
    }

    public static String createJwt(String email, String password, String role){
        return Jwts.builder()
                .claim("password",password)
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expireMs))
                .signWith(secretKey)
                .compact();
    }

    public static boolean validateAccessToken(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredHandler("Expired Token Exception");
        } catch (UnsupportedJwtException | SecurityException | MalformedJwtException | NullPointerException e) {
            throw new JwtInvalidHandler("Invalid Token Exception");
        } catch (Exception e) {
            return false;
        }
    }

}

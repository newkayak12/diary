package com.server.base.common.authorizations;

import com.server.base.common.constants.Constants;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager {
    public <T> String  encrypt(T t){
        String saltValue = Constants.SALT_VALUE;
        String projectName = Constants.PROJECT_NAME;
        Long expireTime = Integer.valueOf(60*60*24).longValue();
        Field[] fields = t.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap<>();
        for(Field field:fields){
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            Object fieldValue = null;
            try {
                fieldValue = field.get(fieldName);
            } catch (IllegalAccessException e) {
            }

            if(!fieldType.isInstance("java.time.LocalDate")||!fieldType.isInstance("java.time.LocalTime")||!fieldType.isInstance("java.time.LocalDateTime")){
                map.put(fieldName, fieldValue);
            }
        }

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(projectName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, saltValue.getBytes(StandardCharsets.UTF_8))
                .compact();
    }
    public <R> R decrypt(R r, String token){
        String saltValue = Constants.SALT_VALUE;
        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object>  claims =  Jwts.parser()
                .setSigningKey(saltValue.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        R result = r;
        modelMapper.map(claims, result);
        return result;
    }
}

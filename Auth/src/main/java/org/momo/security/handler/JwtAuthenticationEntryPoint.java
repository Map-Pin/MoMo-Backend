package org.momo.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.momo.common.BaseResponseDto;
import org.momo.common.status.ErrorStatus;
import org.momo.exception.JwtHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("JwtAuthenticationEntryPoint 실행");
        response.setContentType("application/json");
        BaseResponseDto<Object> baseResponseDto =
                BaseResponseDto.onFailure(
                        ErrorStatus.JWT_TOKEN_NOT_FOUND.getCode(),
                        ErrorStatus.JWT_TOKEN_NOT_FOUND.getMessage(),
                        null);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), baseResponseDto);
    }
}

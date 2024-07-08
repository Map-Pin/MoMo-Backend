package org.momo.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.momo.Member.Entity.Member;
import org.momo.common.BaseResponseDto;
import org.momo.common.status.ErrorStatus;
import org.momo.exception.handler.JwtExpiredHandler;
import org.momo.exception.handler.JwtInvalidHandler;
import org.momo.security.principal.PrincipalDetails;
import org.momo.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");
        if (accessToken == null) {
            filterChain.doFilter(request,response);
            return;
        }
        try{
            JwtUtil.validateAccessToken(accessToken);
            String email = JwtUtil.getEmail(accessToken);

            Member member = Member.builder()
                    .email(email)
                    .build();
            PrincipalDetails principalDetails = PrincipalDetails.createPrincipalDetails(member);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (JwtExpiredHandler e){
            response.setContentType("application/json");
            BaseResponseDto<Object> baseResponseDto = BaseResponseDto.onFailure(
                    ErrorStatus.JWT_ACCESS_TOKEN_EXPIRED.getCode(),
                    ErrorStatus.JWT_ACCESS_TOKEN_EXPIRED.getMessage(),
                    null
            );
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(),baseResponseDto);
            return;
        }catch (JwtInvalidHandler e){
            response.setContentType("application/json");
            BaseResponseDto<Object> baseResponseDto = BaseResponseDto.onFailure(
                    ErrorStatus.JWT_TOKEN_INVALID.getCode(),
                    ErrorStatus.JWT_TOKEN_INVALID.getMessage(),
                    null
            );
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(),baseResponseDto);
            return;
        }
        filterChain.doFilter(request,response);
    }
}

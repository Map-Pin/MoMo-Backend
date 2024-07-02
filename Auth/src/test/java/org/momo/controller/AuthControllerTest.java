package org.momo.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.momo.common.BaseResponseDto;
import org.momo.common.status.SuccessStatus;
import org.momo.dto.AuthRequest;
import org.momo.dto.AuthResponse;
import org.momo.service.AuthService;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    @DisplayName("로그인 성공")
    public void 로그인_성공(){
        AuthRequest.LoginDto loginDto = new AuthRequest.LoginDto("test@gmail.com","test");
        AuthResponse.LoginResponseDto loginResponseDto = AuthResponse.LoginResponseDto.builder()
                .accessToken("accessToken")
                .accessTokenExpiresAt(LocalDateTime.now().plusMinutes(30))
                .build();

        when(authService.login(loginDto)).thenReturn(loginResponseDto);

        BaseResponseDto<AuthResponse.LoginResponseDto> result = authController.login(loginDto);

        assertEquals(SuccessStatus.LOGIN_SUCCESS.getCode(),result.getCode());
        assertEquals(SuccessStatus.LOGIN_SUCCESS.getMessage(),result.getMessage());
        assertEquals("accessToken",result.getResult().getAccessToken());
        assertEquals(LocalDateTime.now().plusMinutes(30).getMinute(), result.getResult().getAccessTokenExpiresAt().getMinute(), 1);
    }


}

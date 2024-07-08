package org.momo.controller;

import lombok.RequiredArgsConstructor;
import org.momo.Base.Entity.Base;
import org.momo.common.BaseResponseDto;
import org.momo.common.status.SuccessStatus;
import org.momo.dto.AuthRequest;
import org.momo.dto.AuthResponse;
import org.momo.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public BaseResponseDto<AuthResponse.LoginResponseDto> login(@RequestBody AuthRequest.LoginDto loginDto) {
        return BaseResponseDto.of(SuccessStatus.LOGIN_SUCCESS.getCode(),SuccessStatus.LOGIN_SUCCESS.getMessage(), authService.login(loginDto));
    }
}

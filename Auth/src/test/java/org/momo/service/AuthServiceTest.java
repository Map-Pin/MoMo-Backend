package org.momo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.momo.Member.Entity.Member;
import org.momo.Member.Repository.MemberRepository;
import org.momo.common.status.ErrorStatus;
import org.momo.dto.AuthRequest;
import org.momo.dto.AuthResponse;
import org.momo.exception.MemberHandler;
import org.momo.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("로그인 성공")
    public void 로그인_성공(){
        try(MockedStatic<JwtUtil> mockedJwtUtil = mockStatic(JwtUtil.class)){
            Member member = Member.builder()
                    .email("test@gmail.com")
                    .password(bCryptPasswordEncoder.encode("test"))
                    .build();
            AuthRequest.LoginDto loginDto = new AuthRequest.LoginDto("test@gmail.com","test");
            when(memberRepository.findMemberByEmail(member.getEmail())).thenReturn(Optional.of(member));
            when(bCryptPasswordEncoder.matches(loginDto.getPassword(),member.getPassword())).thenReturn(true);
            mockedJwtUtil.when(()-> JwtUtil.createJwt(member.getMemberId(), member.getEmail(), null)).thenReturn("mockedJwtToken");

            AuthResponse.LoginResponseDto result = authService.login(loginDto);

            assertEquals("mockedJwtToken", result.getAccessToken());
            assertEquals(LocalDateTime.now().plusMinutes(30).getMinute(), result.getAccessTokenExpiresAt().getMinute(), 1);
        }
    }

    @Test
    @DisplayName("로그인 이메일 불일치")
    public void 이메일불일치(){
        AuthRequest.LoginDto loginDto = new AuthRequest.LoginDto("test@gmail.com","test");
        when(memberRepository.findMemberByEmail(loginDto.getEmail())).thenReturn(Optional.empty());

        MemberHandler exception = assertThrows(MemberHandler.class, () -> {
            authService.login(loginDto);
        });

        assertEquals(ErrorStatus.MEMBER_NOT_FOUND.getMessage(),exception.getReasonHttpStatus().getMessage());
    }

    @Test
    @DisplayName("로그인 비밀번호 불일치")
    public void 비밀번호불일치(){
        Member member = Member.builder()
                .email("test@gmail.com")
                .password(bCryptPasswordEncoder.encode("password"))
                .build();
        AuthRequest.LoginDto loginDto = new AuthRequest.LoginDto("test@gmail.com", "test");
        when(memberRepository.findMemberByEmail(loginDto.getEmail())).thenReturn(Optional.of(member));
        when(bCryptPasswordEncoder.matches(loginDto.getPassword(), member.getPassword())).thenReturn(false);

        MemberHandler exception = assertThrows(MemberHandler.class, () -> {
            authService.login(loginDto);
        });

        assertEquals(ErrorStatus.PASSWORD_NOT_MATCH.getMessage(), exception.getReasonHttpStatus().getMessage());
    }

}

package org.momo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.momo.Member.Entity.Member;
import org.momo.Member.Repository.MemberRepository;
import org.momo.common.status.ErrorStatus;
import org.momo.dto.AuthRequest;
import org.momo.dto.AuthResponse;
import org.momo.exception.MemberHandler;
import org.momo.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 로그인
     */
    public AuthResponse.LoginResponseDto login(AuthRequest.LoginDto loginDto) {
        String email = loginDto.getEmail();
        System.out.println(bCryptPasswordEncoder.encode(loginDto.getPassword()));

        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!bCryptPasswordEncoder.matches(loginDto.getPassword(), member.getPassword()))
            throw new MemberHandler(ErrorStatus.PASSWORD_NOT_MATCH);

        String accessToken = JwtUtil.createJwt(member.getMemberId(), member.getEmail(), null);

        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(30);

        return AuthResponse.LoginResponseDto.builder()
                .accessToken(accessToken)
                .accessTokenExpiresAt(expiredAt)
                .build();

    }
}

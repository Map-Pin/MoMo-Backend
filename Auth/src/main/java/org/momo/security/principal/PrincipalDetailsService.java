package org.momo.security.principal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.momo.Member.Entity.Member;
import org.momo.Member.Repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * security 설정에서 loginProcessingUrl("/login")
 * /login으로 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername 함수 실행");
        Optional<Member> member = memberRepository.findMemberByEmail(username);
        if(member.isEmpty()) throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        return PrincipalDetails.createPrincipalDetails(member.get());
    }
}

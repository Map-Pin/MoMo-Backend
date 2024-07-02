package org.momo.Member.Repository;

import org.momo.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findMemberByEmail(String email);
}

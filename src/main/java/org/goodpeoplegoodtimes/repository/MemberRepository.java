package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

        boolean existsByEmail(String email);
        boolean existsByNickname(String nickname);
}

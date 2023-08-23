package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.dto.member.ProfileResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

        boolean existsByEmail(String email);
        boolean existsByNickname(String nickname);

        Optional<Member> findByEmail(String email);

        @Query("SELECT new org.goodpeoplegoodtimes.domain.dto.member.ProfileResponseDto(m.id,  m.imgNum, m.nickname, m.email) " +
            "FROM Member m WHERE m.email = :email")
        ProfileResponseDto fetchMyProfile(@Param("email") String email);
}

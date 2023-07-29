package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

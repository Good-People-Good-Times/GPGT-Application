package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository <Notice, Long> {

}
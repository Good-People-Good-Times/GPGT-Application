package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository <Party, Long> {
}
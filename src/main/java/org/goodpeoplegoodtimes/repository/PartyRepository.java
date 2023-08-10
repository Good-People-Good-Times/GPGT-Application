package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PartyRepository extends JpaRepository <Party, Long> {

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
        "(p.title, p.category, p.status, p.createdAt) from Party p order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyList(Pageable pageable);

}
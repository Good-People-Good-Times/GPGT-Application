package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;


public interface PartyRepository extends JpaRepository <Party, Long> {

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
            "(p.id, p.title, p.category, p.status, p.createdAt) from Party p order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyList(Pageable pageable);

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
            "(p.id, p.title, p.category, p.status, p.createdAt) from Party p where p.title LIKE %:cond% order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyListBySearch(@Param("cond") String cond, Pageable pageable);

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
            "(p.id, p.title, p.category, p.status, p.createdAt) from Party p where p.category = :category order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyListByCategory(@Param("category") Category category, Pageable pageable);

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto" +
            "(p.id, p.title, p.category, p.status, p.content, p.owner.nickname, p.createdAt) from Party p where p.id = :partyId")
    Optional<PartyDetailResponseDto> fetchPartyDetail(@Param("partyId") Long partyId);

}
package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface PartyRepository extends JpaRepository <Party, Long> {

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
            "(p.id, p.title, p.category, p.status, p.createdAt, p.totalPartyMembers) from Party p order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyList(Pageable pageable);

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
            "(p.id, p.title, p.category, p.status, p.createdAt, p.totalPartyMembers) from Party p where p.title LIKE %:cond% order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyListBySearch(@Param("cond") String cond, Pageable pageable);

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto" +
            "(p.id, p.title, p.category, p.status, p.createdAt, p.totalPartyMembers) from Party p where p.category = :category order by p.createdAt desc")
    Page<PartyListResponseDto> fetchPartyListByCategory(@Param("category") Category category, Pageable pageable);

    @Query("select new org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto" +
            "(p.id, m.id, p.title, p.category, p.status, p.content, m.nickname, p.createdAt) from Party p LEFT JOIN p.owner m where p.id = :partyId")
    Optional<PartyDetailResponseDto> fetchPartyDetail(@Param("partyId") Long partyId);

    @Query("SELECT CASE WHEN (COUNT(p) > 0) " +
            "THEN true " +
            "ELSE false " +
            "END " +
            "FROM Party p JOIN p.owner m WHERE p.id = :partyId AND m.email = :email")
    boolean existsByPartyIdAndOwnerEmail(@Param("partyId") Long partyId, @Param("email") String ownerEmail);


}
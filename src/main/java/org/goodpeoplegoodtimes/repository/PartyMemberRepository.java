package org.goodpeoplegoodtimes.repository;
import org.goodpeoplegoodtimes.domain.PartyMember;
import org.goodpeoplegoodtimes.domain.dto.member.ProfileResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.MyPartyResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party_member.PartyMemberResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {
    @Query("SELECT new org.goodpeoplegoodtimes.domain.dto.party_member.PartyMemberResponseDto(m.id, m.nickname, p.id) " +
            "FROM PartyMember pm " +
            "INNER JOIN pm.member m " +
            "INNER JOIN pm.party p " +
            "WHERE pm.isJoined = FALSE AND p.id = :id " +
            "ORDER BY pm.createdAt desc")
    List<PartyMemberResponseDto> fetchPartyMemberList(@Param("id") Long partyId);
    @Query("SELECT pm FROM PartyMember pm JOIN FETCH pm.party p JOIN FETCH pm.member m WHERE p.id = :partyId AND m.id = :memberId")
    Optional<PartyMember> findPartyMemberByPartyAndMember(@Param("partyId") Long partyId, @Param("memberId") Long memberId);


    @Query("SELECT new org.goodpeoplegoodtimes.domain.dto.party.response.MyPartyResponseDto" +
            "(p.id, p.place, p.title, p.content, m.email, ow.email, p.category, p.status, pm.isJoined) " +
            "from PartyMember pm " +
            "LEFT JOIN pm.party p " +
            "LEFT JOIN p.owner ow " +
            "LEFT JOIN pm.member m " +
            "WHERE m.email = :email " +
            "ORDER BY pm.isJoined DESC, pm.modifiedAt DESC")
    List<MyPartyResponseDto> fetchMyPartyList(@Param("email") String email);


    @Query("SELECT count(pm) FROM PartyMember pm " +
            "LEFT JOIN pm.member m " +
            "LEFT JOIN pm.party p " +
            "WHERE m.email = :email AND p.id = :partyId")
    int existsByMemberAndParty(@Param("email") String email, @Param("partyId") Long partyId);


    @Query("SELECT Count(pm) FROM PartyMember pm LEFT JOIN pm.member m WHERE m.email = :email AND pm.isJoined = true")
    int getJoinedPartyCountByEmail(@Param("email") String email);

    @Query("SELECT Count(pm) FROM PartyMember pm LEFT JOIN pm.member m WHERE m.id = :id AND pm.isJoined = true")
    int getJoinedPartyCountById(@Param("id") Long id);


    @Query("SELECT new org.goodpeoplegoodtimes.domain.dto.member.ProfileResponseDto(m.id,  m.imgNum, m.nickname, m.email) " +
            "FROM PartyMember pm LEFT JOIN pm.member m LEFT JOIN pm.party p WHERE p.id = :partyId AND pm.isJoined = true")
    List<ProfileResponseDto> fetchJoinedPartyMebmerList(@Param("partyId") Long partyId);


}
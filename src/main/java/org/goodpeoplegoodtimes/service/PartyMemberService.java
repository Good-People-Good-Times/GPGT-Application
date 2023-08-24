package org.goodpeoplegoodtimes.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.PartyMember;
import org.goodpeoplegoodtimes.domain.dto.party_member.PartyMemberResponseDto;
import org.goodpeoplegoodtimes.exception.party.PartyNotFoundException;
import org.goodpeoplegoodtimes.repository.PartyMemberRepository;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyMemberService {
    private final PartyService partyService;
    private final MemberService memberService;
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;
    public List<PartyMemberResponseDto> listPartyMember(Long partyId) {
        return partyMemberRepository.fetchPartyMemberList(partyId);
    }
    @Transactional
    public Long joinParty(Long partyId, String email) {
        Member applicant = memberService.getMember(email);
        Party party = partyRepository.findById(partyId).orElseThrow(
                () -> new PartyNotFoundException("해당 파티 정보를 찾을 수 없습니다")
        );
        if (partyMemberRepository.existsByMemberAndParty(email, partyId) >= 1) {
            throw new IllegalArgumentException("이미 파티 신청을 했습니다.");
        }
        partyMemberRepository.save(PartyMember.of(applicant, party));
        return partyId;
    }
    @Transactional
    public Long acceptPartyMember(Long partyId, Long targetId, String ownerEmail) {
        if (!partyService.isOwnerForParty(partyId, ownerEmail)) {
            throw new IllegalArgumentException("파티 주인 x");
        }
        PartyMember partyMember = partyMemberRepository.findPartyMemberByPartyAndMember(partyId, targetId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 파티 멤버가 없습니다")
        );
        partyMember.acceptJoin();
        return partyId;
    }
    @Transactional
    public Long deletePartyMember(Long partyId, Long targetId, String ownerEmail) {
        if (!partyService.isOwnerForParty(partyId, ownerEmail)) {
            throw new IllegalArgumentException("파티 주인 x");
        }
        PartyMember partyMember = partyMemberRepository.findPartyMemberByPartyAndMember(partyId, targetId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 파티 멤버가 없습니다")
        );

        partyMember.getParty().increaseTotalPartyMembers();
        partyMemberRepository.delete(partyMember);
        return partyId;
    }
    @Transactional
    public Long exitPartyMember(Long partyId, Long myId) {
        PartyMember partyMember = partyMemberRepository.findPartyMemberByPartyAndMember(partyId, myId).orElseThrow(
                () -> new IllegalArgumentException("파티에 가입 안되어있음.")
        );
        partyMember.getParty().decreaseTotalPartyMembers();
        partyMemberRepository.delete(partyMember);
        return partyId;
    }
}
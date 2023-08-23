package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.PartyMember;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.dto.member.ProfileResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyUpdateForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.MyPartyResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.exception.party.PartyNotFoundException;
import org.goodpeoplegoodtimes.repository.PartyMemberRepository;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartyService {

    private final MemberService memberService;
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;

    @Transactional
    public Long create(PartyForm partyForm, Authentication authentication) {

        Member member = memberService.getMember(authentication.getName());
        Party party = Party.of(partyForm, member);

        PartyMember partyMember = PartyMember.of(member, party);
        partyMember.acceptJoin();

        partyMemberRepository.save(partyMember);
        return partyRepository.save(party).getId();
    }

    @Transactional
    public void update(PartyUpdateForm partyUpdateForm) {
        Party party = partyRepository.findById(partyUpdateForm.getPartyId()).get();
        party.update(partyUpdateForm);
    }

    @Transactional
    public void delete(Long partyId) {
        Party party = partyRepository.findById(partyId).get();
        partyRepository.delete(party);
    }


    public Page<PartyListResponseDto> fetchList(String cond, Category category, Pageable pageable) {
        if (isSearchingByCondition(cond)) {
            return partyRepository.fetchPartyListBySearch(cond, pageable);
        } else if (isFilteringByCategory(category)) {
            return partyRepository.fetchPartyListByCategory(category, pageable);
        } else {
            return partyRepository.fetchPartyList(pageable);
        }
    }

    public boolean isOwnerForParty(Long partyId, String ownerEmail) {
        return partyRepository.existsByPartyIdAndOwnerEmail(partyId, ownerEmail);
    }

    public PartyDetailResponseDto findPartyDetailById(Long id) {
        PartyDetailResponseDto partyDetailResponseDto = partyRepository.fetchPartyDetail(id).orElseThrow(
            () -> new PartyNotFoundException("파티를 찾을 수 없습니다.")
        );
        partyDetailResponseDto.setJoinedPartyCount(partyMemberRepository.getJoinedPartyCount(partyDetailResponseDto.getMember().getEmail()));
        return partyDetailResponseDto;
    }

    public List<MyPartyResponseDto> getMyPartyList(String email) {
        return partyMemberRepository.fetchMyPartyList(email);
    }

    public List<ProfileResponseDto> getJoinedPartyMemberList(Long partyId) {
        return partyMemberRepository.fetchJoinedPartyMebmerList(partyId);
    }

    public boolean isAlreadyPartyJoinApply(String email, Long partyId) {
        return partyMemberRepository.existsByMemberAndParty(email, partyId) >= 1;
    }

    private boolean isSearchingByCondition(String cond) {
        return cond != null;
    }

    private boolean isFilteringByCategory(Category category) {
        return category != null;
    }
}


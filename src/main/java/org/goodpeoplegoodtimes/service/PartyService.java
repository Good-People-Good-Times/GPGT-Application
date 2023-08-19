package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
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
    public Long createParty(PartyForm partyForm, Authentication authentication) {
        Member member = memberService.getMember(authentication.getName());
        return partyRepository.save(Party.of(partyForm, member)).getId();
    }

    public Page<PartyListResponseDto> getPartyList(String cond, Category category, Pageable pageable) {
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
        return partyRepository.fetchPartyDetail(id).orElseThrow(
                () -> new PartyNotFoundException("파티를 찾을 수 없습니다.")
        );
    }

    public List<PartyDetailResponseDto> getMyPartyList(String email) {
        return partyMemberRepository.fetchMyPartyList(email);
    }

    private boolean isSearchingByCondition(String cond) {
        return cond != null;
    }

    private boolean isFilteringByCategory(Category category) {
        return category != null;
    }
}


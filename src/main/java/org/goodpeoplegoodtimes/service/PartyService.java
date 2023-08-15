package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.exception.party.PartyNotFoundException;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartyService {

    private final MemberService memberService;
    private final PartyRepository partyRepository;

    @Transactional
    public Long createParty(PartyForm partyForm, Authentication authentication) {
        // 회원 조회
        Member member = memberService.getMember(authentication.getName());
        return partyRepository.save(Party.of(partyForm, member)).getId();
    }

    public Page<PartyListResponseDto> getPartyList(Pageable pageable) {
        return partyRepository.fetchPartyList(pageable);
    }


    public Page<PartyListResponseDto> getPartyList(String cond, Pageable pageable) {
        return partyRepository.fetchPartyListBySearch(cond, pageable);
    }

    public Page<PartyListResponseDto> getPartyList(Category category, Pageable pageable) {
        return partyRepository.fetchPartyListByCategory(category, pageable);
    }

    public PartyDetailResponseDto findPartyDetailById(Long id) {
        return partyRepository.fetchPartyDetail(id).orElseThrow(
            () -> new PartyNotFoundException("파티를 찾을 수 없습니다.")
        );
    }
}

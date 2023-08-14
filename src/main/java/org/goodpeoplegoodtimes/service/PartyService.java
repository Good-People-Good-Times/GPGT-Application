package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyDetailResponseDto;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.exception.party.PartyNotFoundException;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartyService {

    private final MemberService memberService;
    private final PartyRepository partyRepository;

    public Long save(PartyForm partyForm, Authentication authentication) {
        // 회원 조회
        Member member = memberService.getMember(authentication.getName());
        return partyRepository.save(Party.of(partyForm, member)).getId();
    }

    @Transactional(readOnly = true)
    public Page<PartyListResponseDto> getPartyList(@Nullable String cond, Pageable pageable) {
        if (!cond.isEmpty()) return partyRepository.fetchPartyListBySearch(cond, pageable);
        return partyRepository.fetchPartyList(pageable);
    }

    @Transactional(readOnly = true)
    public PartyDetailResponseDto findPartyDetailById(Long id) {
        return partyRepository.fetchPartyDetail(id).orElseThrow(
            () -> new PartyNotFoundException("파티를 찾을 수 없습니다.")
        );
    }
}

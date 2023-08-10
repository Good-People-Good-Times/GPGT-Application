package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Member member = memberService.getMember(authentication.getName());
        return partyRepository.save(Party.of(partyForm, member)).getId();
    }

    @Transactional(readOnly = true)
    public Page<PartyListResponseDto> getPartyList(Pageable pageable) {
        return partyRepository.fetchPartyList(pageable);
    }



}

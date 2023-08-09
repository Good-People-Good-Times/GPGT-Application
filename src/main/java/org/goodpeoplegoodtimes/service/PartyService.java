package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.dto.party.PartyForm;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Long save(PartyForm partyForm, Member member) {
        // 회원 조회
        return partyRepository.save(Party.of(partyForm, member)).getId();
    }

    @Transactional(readOnly = true)
    public List<Party> getPartyList() {
        return partyRepository.findAll();
    }

}
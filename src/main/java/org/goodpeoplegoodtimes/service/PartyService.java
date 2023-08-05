package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.dto.PartyDto;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;

    public Long save(PartyDto partyDto) {
        return partyRepository.save(Party.of(partyDto)).getParty_id();

    }
}
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

    /**
     * 인증된 사용자의 이름을 기반으로 해당 회원의 정보를 조회하고, 파티를 저장
     *
     * @param partyForm 파티 생성 정보
     * @param authentication 현재 인증 정보
     * @return 생성된 파티의 ID
     */
    public Long save(PartyForm partyForm, Authentication authentication) {
        Member member = memberService.getMember(authentication.getName());
        return partyRepository.save(Party.of(partyForm, member)).getId();
    }

    /**
     * 페이지 정보를 기반으로 파티 목록을 조회
     * 조회된 결과는 PartyListResponseDto 형태의 페이지로 반환함
     * Pageable은 페이징을 위한 파라미터를 제공하며, Page는 조회된 결과를 페이징 형태로 관리
     *
     * -> Pageable 객체를 사용하는 이유는 한 번에 모든 리스트를 반환하여 사용할 경우
     * 전체적으로 성능에 문제가 생기기 때문에 조금씩 필요한 만큼만 로드해서 사용하기 위함.
     * ex) 게시판을 보았을 때, 1페이지.. 2페이지..3페이지... 인 것과 같은 개념임.
     *
     * @param pageable 페이지 정보 (페이지 번호, 페이지 크기 등)
     * @return 파티 목록을 담은 페이지 객체 (조회된 데이터와 페이징 정보를 포함)
     */
    @Transactional(readOnly = true)
    public Page<PartyListResponseDto> getPartyList(Pageable pageable) {
        return partyRepository.fetchPartyList(pageable);
    }



}

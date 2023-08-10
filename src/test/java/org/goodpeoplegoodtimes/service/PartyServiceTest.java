package org.goodpeoplegoodtimes.service;

import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PartyServiceTest {

    @InjectMocks
    private PartyService partyService;

    @Mock
    private MemberService memberService;

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private Authentication authentication;

    @Test
    @DisplayName("[CREATE] 파티 저장")
    public void saveTest() {

        // given
        PartyForm partyForm = new PartyForm();
        Member member = Member.builder()
            .email("email@email.com")
            .password("123123123")
            .nickname("닉네임")
            .imgNum(1)
            .role(Role.USER)
            .build();

        Party party = Party.of(partyForm, member);

        given(authentication.getName()).willReturn(member.getEmail());
        given(memberService.getMember(authentication.getName())).willReturn(member);
        given(partyRepository.save(any(Party.class))).willReturn(party);

        // when
        Long savedId = partyService.save(partyForm, authentication);

        // then
        then(partyRepository).should().save(any(Party.class));
        assertThat(savedId).isEqualTo(party.getId());
    }


    @Test
    @DisplayName("[READ] 파티 리스트 조회")
    public void getPartyList() {

        // given
        Pageable pageable = PageRequest.of(0, 4); // 페이지 번호와 크기 지정
        List<PartyListResponseDto> content = Arrays.asList(
            new PartyListResponseDto("Title1", Category.TAXI, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title2", Category.EXERCISE, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title3", Category.ETC, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title4", Category.MEAL, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title5", Category.EXERCISE, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title6", Category.HOBBY, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title7", Category.MOVIE, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title8", Category.EXERCISE, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title9", Category.TAXI, PartyStatus.RECRUITING, LocalDateTime.now()),
            new PartyListResponseDto("Title10", Category.TAXI, PartyStatus.RECRUITING, LocalDateTime.now())
        );

        Page<PartyListResponseDto> expectedPage = new PageImpl<>(content, pageable, content.size());
        given(partyRepository.fetchPartyList(pageable)).willReturn(expectedPage);

        // when
        Page<PartyListResponseDto> resultPage = partyService.getPartyList(pageable);

        // then
        then(partyRepository).should().fetchPartyList(pageable);
        assertThat(resultPage).isEqualTo(expectedPage);

    }
}

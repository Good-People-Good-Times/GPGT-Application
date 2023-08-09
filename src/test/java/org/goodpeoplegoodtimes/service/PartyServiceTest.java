package org.goodpeoplegoodtimes.service;

import org.assertj.core.api.Assertions;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.dto.SignupForm;
import org.goodpeoplegoodtimes.dto.party.PartyForm;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class PartyServiceTest {

    @Autowired
    PartyService partyService;

    @Autowired
    MemberService memberService;

    @Autowired
    PartyRepository partyRepository;

    @BeforeEach
    public void init() {
        Member member = Member.builder()
            .email("test@test.com")
            .password("123123123")
            .nickname("테스터")
            .imgNum(3)
            .role(Role.USER)
            .build();

        SignupForm signupForm = new SignupForm();
        signupForm.setEmail(member.getEmail());
        signupForm.setPassword(member.getPassword());
        signupForm.setNickname(member.getNickname());
        signupForm.setImgNum(member.getImgNum());
        memberService.save(signupForm);
    }


    @Test
    public void 파티_생성_테스트() {

        PartyForm partyForm = new PartyForm();
        partyForm.setTitle("기흥 택시 팟");
        partyForm.setCategory(Category.TAXI);
        partyForm.setContent("기흥 9시 택시 같이 탈 사람");

        // 파티 데이터베이스에 성공적으로 저장.
        Member findMember = memberService.getMember("test@test.com");
        Long savedPartyId = partyService.save(partyForm, findMember);

        // 파티 조회.
        Party findParty = partyRepository.findById(savedPartyId).orElseThrow(
            () -> new IllegalStateException("해당 id 값의 party를 찾을 수 없습니다.")
        );

        Assertions.assertThat(findParty.getTitle()).isEqualTo(partyForm.getTitle());
        Assertions.assertThat(findParty.getOwner().getEmail()).isEqualTo("test@test.com");

        System.out.println(findParty.getOwner().getId() == findMember.getId());
    }

}
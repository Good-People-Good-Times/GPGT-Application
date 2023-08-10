package org.goodpeoplegoodtimes.repository;


import org.assertj.core.api.Assertions;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.response.PartyListResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.Random;

@DataJpaTest
@ActiveProfiles("test")
@MockBean(JpaMetamodelMappingContext.class)
class PartyRepositoryTest {

    @Autowired
    PartyRepository partyRepository;

    @Autowired
    EntityManager em;

    private Member member;
    private PartyForm partyForm;

    @BeforeEach
    void init() {
        member = Member.builder()
            .email("test@test.com")
            .password("123123")
            .nickname("테스트")
            .imgNum(1)
            .role(Role.USER)
            .build();
        em.persist(member);

        Random random = new Random();
        Category[] categories = Category.values();
        String[] titles = {"파티 초대합니다!", "즐거운 모임", "주말 파티!", "함께 즐겁게!", "친목 도모"};
        String[] contents = {"즐거운 시간 보내고 싶어요!", "파티에 오세요!", "함께 놀아요!", "주말엔 파티!", "참여 환영합니다!"};

        for (int i = 1; i <= 10; i++) {
            partyForm = new PartyForm();
            partyForm.setTitle(titles[random.nextInt(titles.length)]);
            partyForm.setCategory(categories[random.nextInt(categories.length)]);
            partyForm.setContent(contents[random.nextInt(contents.length)]);
            em.persist(Party.of(partyForm, member));
        }

        em.flush();
    }


    @Test
    public void 파티_저장_테스트() {
        // given
        PartyForm partyForm = new PartyForm();
        partyForm.setTitle("기흥택시 파티");
        partyForm.setCategory(Category.TAXI);
        partyForm.setContent("저와 함께 기흥에서 택시타고 학교로 가실 분??");

        Party party = Party.of(partyForm, member);

        // when
        partyRepository.save(party);

        // then
        Assertions.assertThat(party.getOwner().getId()).isEqualTo(member.getId());
        Assertions.assertThat(party.getTitle()).isEqualTo(partyForm.getTitle());
    }

    @Test
    public void 파티_리스트_테스트() {

        // given, when
        Page<PartyListResponseDto> partyList = partyRepository.fetchPartyList(PageRequest.of(0, 3));

        // then
        Assertions.assertThat(partyList.getContent().size()).isEqualTo(3); // 실제 컨텐츠 크기 확인
        Assertions.assertThat(partyList.getSize()).isEqualTo(3); // 페이지 사이즈 확인
        Assertions.assertThat(partyList.getTotalPages()).isEqualTo(4); // 전체 페이지 수 확인
        Assertions.assertThat(partyList.getTotalElements()).isEqualTo(10); // 전체 파티 수 확인
        Assertions.assertThat(partyList.getNumber()).isEqualTo(0); // 현재 페이지 번호 확인
    }


}
package org.goodpeoplegoodtimes;


import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.Party;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.repository.MemberRepository;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner testDataSet(MemberRepository memberRepository,
                                         PartyRepository partyRepository,
                                         PasswordEncoder passwordEncoder) {
        return args -> {

            // 멤버 데이터 5개 생성
            for (int i = 1; i <= 5; i++) {
                memberRepository.save(Member.builder()
                    .email("test" + i + "@test.com")
                    .password(passwordEncoder.encode("123123123"))
                    .nickname("테스터" + i)
                    .imgNum(i % 5 == 0 ? 1 : i)
                    .role(Role.USER)
                    .build());
            }


            Random random = new Random();
            Category[] categories = Category.values();
            PartyStatus[] statuses = PartyStatus.values();
            String[] titles = {
                "기흥 택시 팟 구함", "학식 같이 먹을 사람~~~", "영화 보러 가요", "카페에서 코딩하기", "헬스장 운동 파트너",
                "밥팟 같이 하실?", "택시팟 모집합니다", "영화팟 함께해요!", "카페 취미팟", "운동팟 같이해요",
                "기타 취미팟", "카페에서 읽기", "취미 공유 모임", "함께 책 읽기", "운동팟 가입해요",
                "점심 식사 파트너", "택시팟 참여하세요", "코딩 스터디 모임", "영화 같이 보러 가기", "헬스장 파트너 찾기",
                "택시팟 찾습니다", "점심 밥팟 구함", "영화 좋아하세요?", "카페에서 노래 듣기", "헬스장에서 같이 하기",
                "기타 모임 참여", "카페에서 대화 나누기", "운동을 시작하세요!", "저녁 식사 파트너", "출근 택시팟 구해요"
            };
            String[] contents = {
                "아침 9시에 기흥에서 같이 택시 탈 사람..?", "오늘 학식 짬뽕인데 ㄱㄱ", "영화 보러 같이 가실 분 찾습니다.", "카페에서 코딩하실 분 구해요!", "함께 운동할 파트너 찾습니다.",
                "점심 같이 드실 분?", "같이 택시 탈 사람 찾아요!", "영화 좋아하시는 분?", "카페에서 취미 생활 즐기실 분!", "헬스장에서 같이 운동하실 분?",
                "다양한 취미를 즐기실 분?", "책 읽기 좋아하시는 분 찾아요!", "다양한 취미 공유하실 분?", "조용한 카페에서 책 읽기 좋아하시는 분?", "저녁에 함께 운동할 사람 찾습니다!",
                "점심 시간에 같이 식사하실 분?", "같이 출근할 택시팟 구합니다!", "카페에서 코딩 스터디 하실 분?", "이번 주말 영화 보러 가실 분?", "함께 헬스장에서 운동하실 분?",
                "같이 택시 타고 가실 분?", "점심 밥팟 참여하실 분?", "주말에 영화 보실 분?", "카페에서 좋아하는 노래 듣기 좋아하시는 분?", "함께 헬스장에서 땀 흘리실 분?",
                "다양한 취미를 같이 즐기실 분?", "좋은 카페에서 대화하실 분?", "운동을 시작하실 분?", "저녁에 식사하실 분?", "아침에 출근할 택시팟 구합니다!"
            };


            // 파티 데이터 30개 생성
            for (int i = 0; i < 30; i++) {
                partyRepository.save(Party.builder()
                    .title(titles[i])
                    .category(categories[random.nextInt(categories.length)])
                    .status(statuses[random.nextInt(statuses.length)])
                    .content(contents[i])
                    .owner(memberRepository.findById((long) (random.nextInt(5) + 1)).orElseThrow())
                    .build());
            }

        };
    }
}

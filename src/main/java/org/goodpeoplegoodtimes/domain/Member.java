package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.dto.SignupForm;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private int imgNum;

    /**
     * Dto -> Entity 변환 메서드
     * @param signupForm
     */
    public static Member of(SignupForm signupForm) {
        return Member.builder()
            .email(signupForm.getEmail())
            .password(signupForm.getPassword())
            .nickname(signupForm.getNickname())
            .imgNum(signupForm.getImgNum())
            .build();
    }

}

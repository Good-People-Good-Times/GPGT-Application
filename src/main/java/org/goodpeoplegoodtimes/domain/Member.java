package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.dto.SignupDto;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    /** Dto > Entity 변환 메서드
     *
     * @param signupDto
     */
    public static Member of(SignupDto signupDto) {
        return Member.builder()
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .nickname(signupDto.getNickname())
                .imgNum(signupDto.getImgNum())
                .build();
    }

}

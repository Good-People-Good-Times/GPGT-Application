package org.goodpeoplegoodtimes.domain;
import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.domain.dto.auth.SignupForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<PartyMember> partyMembers = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Party> parties = new ArrayList<>();

    public static Member of(SignupForm signupForm, String encodedPassword, int imgNum) {
        return Member.builder()
                .email(signupForm.getEmail())
                .password(encodedPassword)
                .nickname(signupForm.getNickname())
                .imgNum(imgNum)
                .role(Role.USER)
                .build();
    }
}
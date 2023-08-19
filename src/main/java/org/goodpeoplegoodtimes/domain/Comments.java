package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments extends BaseEntity {

    @Id
    @Column(name = "comments_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    public static Comments of(String comment, Member member, Party party) {
        return Comments.builder()
            .content(comment)
            .member(member)
            .party(party)
            .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }

}

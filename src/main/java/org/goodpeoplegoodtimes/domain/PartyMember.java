package org.goodpeoplegoodtimes.domain;
import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;
import javax.persistence.*;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyMember extends BaseEntity {
    @Id
    @Column(name = "party_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    private boolean isJoined;
    public static PartyMember of(Member member, Party party) {
        return PartyMember.builder()
                .member(member)
                .party(party)
                .build();
    }
    public void acceptJoin() {
        this.isJoined = true;
        party.increaseTotalPartyMembers();
    }
}
package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyUpdateForm;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Party extends BaseEntity {

    @Id
    @Column(name = "party_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "party_title")
    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private PartyStatus status;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_owner")
    private Member owner;

    private int totalPartyMembers;

    private int currentPartyMembers;

    private LocalDateTime meetingPlannedTime;

    public static Party of(PartyForm partyForm, Member member) {
        return Party.builder()
            .owner(member)
            .title(partyForm.getTitle())
            .status(PartyStatus.RECRUITING)
            .content(partyForm.getContent())
            .category(partyForm.getCategory())
            .totalPartyMembers(partyForm.getTotalPartyMembers())
            .build();
    }

    public void update(PartyUpdateForm updateForm) {
        this.title = updateForm.getTitle();
        this.content = updateForm.getContent();
        this.category = updateForm.getCategory();
        this.totalPartyMembers = updateForm.getTotalPartyMembers();
    }

    public void decreaseTotalPartyMembers() {
        this.currentPartyMembers -= 1;
    }

    public void increaseTotalPartyMembers() {
        this.currentPartyMembers += 1;
        if (currentPartyMembers == totalPartyMembers) {
            this.status = PartyStatus.CLOSE;
        }
    }

}

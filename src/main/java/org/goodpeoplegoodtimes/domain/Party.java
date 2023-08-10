package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.domain.dto.party.request.PartyForm;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "party_owner")
    private Member owner;


    public static Party of(PartyForm partyForm, Member member) {
        return Party.builder()
            .title(partyForm.getTitle())
            .category(partyForm.getCategory())
            .status(PartyStatus.RECRUITING)
            .content(partyForm.getContent())
            .owner(member)
            .build();
    }

}

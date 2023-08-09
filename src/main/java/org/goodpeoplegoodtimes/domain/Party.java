package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.dto.party.PartyForm;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Party {

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


    /**
     * @param partyForm -> 사용자로부터 입력받은 폼
     * @param member    -> 파티 소유자.
     */
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

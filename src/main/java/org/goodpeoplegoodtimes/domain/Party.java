package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.dto.PartyDto;

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
    private Long party_id;

    @Column
    private String title;

    @Column
    private String type;

    @Column
    private String date;

    @Column
    private String people;

    @Column
    private String place;

    @Column
    private String content;

    public static Party of(PartyDto partyDto) {
        return Party.builder()
                .title(partyDto.getTitle())
                .type(partyDto.getType())
                .date(partyDto.getDate())
                .people(partyDto.getPeople())
                .place(partyDto.getPlace())
                .content(partyDto.getContent())
                .build();
    }
}

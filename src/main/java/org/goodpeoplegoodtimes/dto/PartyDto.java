package org.goodpeoplegoodtimes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto {

    private Long party_id;

    private String title;

    private String type;

    private String date;

    private String people;

    private String place;

    private String content;
}

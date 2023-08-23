package org.goodpeoplegoodtimes.domain.dto.party.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goodpeoplegoodtimes.domain.constant.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyForm {

    private Long partyId;
    private String title;
    private String content;
    private Category category;
    private int totalPartyMembers;
    private String date;
    private String time;
    private String place;

}

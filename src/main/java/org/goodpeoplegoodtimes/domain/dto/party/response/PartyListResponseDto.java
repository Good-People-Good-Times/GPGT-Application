package org.goodpeoplegoodtimes.domain.dto.party.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyListResponseDto {

    private Long id;
    private String title;
    private Category category;
    private PartyStatus status;
    private LocalDateTime dateTime;
    private int totalPartyMembers;
    private int currentPartyMembers;
    private String place;

}

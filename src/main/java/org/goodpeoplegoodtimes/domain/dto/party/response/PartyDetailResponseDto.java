package org.goodpeoplegoodtimes.domain.dto.party.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class PartyDetailResponseDto {

    private Long partyId;
    private Long ownerId;
    private String title;
    private Category category;
    private PartyStatus status;
    private String content;
    private String owner;
    private LocalDateTime dateTime;
    private int totalPartyMembers;
    private int currentPartyMembers;

}

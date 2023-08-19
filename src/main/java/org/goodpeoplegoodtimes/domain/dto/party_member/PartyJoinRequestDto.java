package org.goodpeoplegoodtimes.domain.dto.party_member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyJoinRequestDto {
    private Long partyId;
    private Long ownerId;
    private Long applicantId;
}

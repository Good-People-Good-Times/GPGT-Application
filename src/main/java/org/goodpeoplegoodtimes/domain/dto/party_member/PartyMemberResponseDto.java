package org.goodpeoplegoodtimes.domain.dto.party_member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyMemberResponseDto {
    private Long memberId;
    private String nickname;
    private Long partyId;
}

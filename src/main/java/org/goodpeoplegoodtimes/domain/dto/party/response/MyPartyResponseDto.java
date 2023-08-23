package org.goodpeoplegoodtimes.domain.dto.party.response;

import lombok.Data;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;


@Data
public class MyPartyResponseDto {

    private Long partyId;
    private String place;
    private String title;
    private String content;
    private Category category;
    private String email;
    private String ownerEmail;
    private PartyStatus status;
    private boolean isJoined;

    public MyPartyResponseDto(Long partyId,
                              String place,
                              String title,
                              String content,
                              String email,
                              String OwnerEmail,
                              Category category,
                              PartyStatus status,
                              boolean isJoined) {

        this.partyId = partyId;
        this.place = place;
        this.title = title;
        this.content = content;
        this.email = email;
        this.ownerEmail = OwnerEmail;
        this.category = category;
        this.status = status;
        this.isJoined = isJoined;
    }
}


package org.goodpeoplegoodtimes.domain.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileResponseDto {

    private Long memberId;
    private int imgNum;
    private String nickname;
    private String email;
    private int partyJoinedCount;

    public ProfileResponseDto(Long memberId, int imgNum, String nickname, String email) {
        this.memberId = memberId;
        this.imgNum = imgNum;
        this.nickname = nickname;
        this.email = email;
    }
}

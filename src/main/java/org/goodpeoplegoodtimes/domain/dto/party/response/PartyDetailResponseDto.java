package org.goodpeoplegoodtimes.domain.dto.party.response;

import lombok.Data;
import org.goodpeoplegoodtimes.domain.constant.Category;
import org.goodpeoplegoodtimes.domain.constant.PartyStatus;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.domain.dto.member.MemberResponseDto;

import java.time.LocalDateTime;


@Data
public class PartyDetailResponseDto {

    private Long partyId;
    private String title;
    private Category category;
    private PartyStatus status;
    private String content;
    private LocalDateTime dateTime;
    private MemberResponseDto member;
    private int totalPartyMembers;
    private int currentPartyMembers;
    private String place;
    private int joinedPartyCount;

    public PartyDetailResponseDto(Long partyId, String title, Category category, PartyStatus status,
                                  String content, LocalDateTime dateTime, Long memberId, String email,
                                  String nickname, int imgNum, Role role, int totalPartyMembers,
                                  int currentPartyMembers, String place) {
        this.partyId = partyId;
        this.title = title;
        this.category = category;
        this.status = status;
        this.content = content;
        this.dateTime = dateTime;
        this.member = new MemberResponseDto(memberId, email, nickname, imgNum, role);
        this.totalPartyMembers = totalPartyMembers;
        this.currentPartyMembers = currentPartyMembers;
        this.place = place;
    }
}


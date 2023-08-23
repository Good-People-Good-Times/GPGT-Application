package org.goodpeoplegoodtimes.domain.dto.comments;


import lombok.Data;
import org.goodpeoplegoodtimes.domain.constant.Role;
import org.goodpeoplegoodtimes.domain.dto.member.MemberResponseDto;

import java.time.LocalDateTime;

@Data
public class CommentsResponseDto {

    private String content;
    private Long commentsId;
    private LocalDateTime dateTime;
    private MemberResponseDto member;

    public CommentsResponseDto(Long commentsId, String content, LocalDateTime dateTime,
                               Long memberId, String email, String nickname, int imgNum, Role role) {
        this.content = content;
        this.commentsId = commentsId;
        this.dateTime = dateTime;
        this.member = new MemberResponseDto(memberId, email, nickname, imgNum, role);
    }
}

package org.goodpeoplegoodtimes.domain.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponseDto {
    private Long commentsId;
    private String owner;
    private String content;
    private LocalDateTime dateTime;
}

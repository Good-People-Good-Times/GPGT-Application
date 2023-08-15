package org.goodpeoplegoodtimes.domain.dto.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String owner;
    private LocalDateTime dateTime;

    public BoardResponseDto(Long id, String title, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
    }

    public BoardResponseDto(Long id, String title, String content, String owner, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.owner = owner;
        this.dateTime = dateTime;
    }
}

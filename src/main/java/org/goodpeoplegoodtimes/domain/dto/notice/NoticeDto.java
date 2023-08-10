package org.goodpeoplegoodtimes.domain.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private Long notice_id;
    private String subject;
    private String content;
    private LocalDateTime date;

}

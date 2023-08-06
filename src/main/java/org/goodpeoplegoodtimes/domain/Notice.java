package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.dto.NoticeDto;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    
    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notice_id;

    @Column(columnDefinition = "TEXT")
    private String subject;

    @Column
    private String content;

    @CreatedDate
    private LocalDateTime date;
    
    /**
     * Dto -> Entity 변환 메서드
     * @param NoticeDto
     */
    public static Notice of(NoticeDto noticeDto) {
        return Notice.builder()
                .subject(noticeDto.getSubject())
                .content(noticeDto.getContent())
                .date(noticeDto.getDate())
                .build();
    }
}

package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;
import org.goodpeoplegoodtimes.domain.dto.notice.NoticeDto;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {
    
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


    public static Notice of(NoticeDto noticeDto) {
        return Notice.builder()
                .subject(noticeDto.getSubject())
                .content(noticeDto.getContent())
                .date(noticeDto.getDate())
                .build();
    }
}

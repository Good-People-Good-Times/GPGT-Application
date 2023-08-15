package org.goodpeoplegoodtimes.domain;

import lombok.*;
import org.goodpeoplegoodtimes.domain.base.BaseEntity;
import org.goodpeoplegoodtimes.domain.dto.board.BoardForm;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Board of(BoardForm requestDto, Member member) {
        return Board.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .build();
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}

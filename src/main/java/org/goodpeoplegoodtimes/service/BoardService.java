package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Board;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.dto.board.BoardForm;
import org.goodpeoplegoodtimes.domain.dto.board.BoardResponseDto;
import org.goodpeoplegoodtimes.exception.board.BoardNotFoundException;
import org.goodpeoplegoodtimes.repository.BoardRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final MemberService memberService;
    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(BoardForm requestDto, Authentication authentication) {
        Member member = memberService.getMember(authentication.getName());
        return boardRepository.save(Board.of(requestDto, member)).getId();
    }

    public List<BoardResponseDto> getBoardList() {
        return boardRepository.getBoardList();
    }

    public BoardResponseDto findBoardDetailById(Long id) {
        return boardRepository.getBoardDetail(id).orElseThrow(
                () -> new BoardNotFoundException("해당하는 게시글을 찾을 수 없습니다")
        );
    }

}

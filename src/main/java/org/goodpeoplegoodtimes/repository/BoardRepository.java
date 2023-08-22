package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Board;
import org.goodpeoplegoodtimes.domain.dto.board.BoardResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository <Board, Long> {


    @Query("select new org.goodpeoplegoodtimes.domain.dto.board.BoardResponseDto" +
            "(b.id, b.title, b.createdAt) from Board b order by b.createdAt desc")
    List<BoardResponseDto> getBoardList();

    @Query("select new org.goodpeoplegoodtimes.domain.dto.board.BoardResponseDto" +
            "(b.id, b.title, b.content, b.member.nickname, b.createdAt) from Board b where b.id =:id")
    Optional<BoardResponseDto> getBoardDetail(@Param("id") Long id);

}
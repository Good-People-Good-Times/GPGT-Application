package org.goodpeoplegoodtimes.repository;

import org.goodpeoplegoodtimes.domain.Comments;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.dto.comments.CommentsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("SELECT new org.goodpeoplegoodtimes.domain.dto.comments.CommentsResponseDto" +
        "(c.id, c.content, c.modifiedAt, m.id, m.email, m.nickname, m.imgNum, m.role) " +
        "FROM Comments c " +
        "LEFT JOIN c.party p " +
        "LEFT JOIN c.member m " +
        "WHERE p.id = :id " +
        "ORDER BY c.createdAt desc")
    List<CommentsResponseDto> fetchCommentsList(@Param("id") Long id);

    boolean existsCommentsByIdAndMember(Long id, Member member);
}


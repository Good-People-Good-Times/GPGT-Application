package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.Comments;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.dto.comments.CommentsResponseDto;
import org.goodpeoplegoodtimes.exception.party.PartyNotFoundException;
import org.goodpeoplegoodtimes.repository.CommentsRepository;
import org.goodpeoplegoodtimes.repository.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PartyRepository partyRepository;
    private final MemberService memberService;

    public List<CommentsResponseDto> listComments(Long partyId) {
        return commentsRepository.fetchCommentsList(partyId);
    }

    @Transactional
    public Long saveComments(String comment, String email, Long partyId) {
        Comments comments = commentsRepository.save(Comments.of(
            comment,
            memberService.getMember(email),
            partyRepository.findById(partyId).orElseThrow(
                () -> new PartyNotFoundException("파티를 찾을 수 없습니다")
            )));
        return partyId;
    }

    @Transactional
    public void updateComments(String content, String email, Long commentsId) {
        Member member = memberService.getMember(email);
        if (!commentsRepository.existsCommentsByIdAndMember(commentsId, member)) {
            throw new IllegalArgumentException("예외");
        }

        Comments comments = commentsRepository.findById(commentsId).get();
        comments.updateContent(content);
    }

    @Transactional
    public Long deleteComments(String email, Long commentsId) {
        Member member = memberService.getMember(email);
        if (!commentsRepository.existsCommentsByIdAndMember(commentsId, member)) {
            throw new IllegalArgumentException("예외");
        }

        Comments comments = commentsRepository.findById(commentsId).get();
        commentsRepository.delete(comments);
        return commentsId;
    }
}


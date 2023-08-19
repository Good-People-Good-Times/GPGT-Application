package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.dto.comments.CommentsRequestDto;
import org.goodpeoplegoodtimes.service.CommentsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public String createComments(String comment, Long partyId, Authentication authentication) {
        Long id = commentsService.saveComments(comment, authentication.getName(), partyId);
        return "redirect:/party/" + id;
    }

}

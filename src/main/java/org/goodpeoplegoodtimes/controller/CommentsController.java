package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.dto.comments.CommentsRequestDto;
import org.goodpeoplegoodtimes.service.CommentsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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

    @PostMapping("/{commentsId}/update")
    public String updateComments(@PathVariable("commentsId") Long commentsId, Long partyId, String comment, Authentication authentication) {
        log.info("\n============== updateComments Controller ==============");
        commentsService.updateComments(comment, authentication.getName(), commentsId);
        return "redirect:/party/" + partyId;
    }

    @PostMapping("/{commentsId}/delete")
    public String deleteComments(@PathVariable("commentsId") Long commentsId, Long partyId, Authentication authentication) {
        log.info("\n============== deleteComments Controller ==============");
        commentsService.deleteComments(authentication.getName(), commentsId);
        return "redirect:/party/" + partyId;
    }

}

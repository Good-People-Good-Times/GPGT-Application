package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.dto.board.BoardForm;
import org.goodpeoplegoodtimes.service.BoardService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String displayNoticeList(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "notice/notice_list";
    }

    @GetMapping("/create")
    public String displayNoticeCreationForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "notice/notice_form";
    }

    @PostMapping(value = "/create")
    public String createBoard(@Valid BoardForm boardForm,
                              BindingResult bindingResult,
                              Authentication authentication,
                              Model model) {
        if (bindingResult.hasGlobalErrors()) {
            String errorMsg = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("boardForm", boardForm);
            return "notice/notice_form";
        }
        Long boardId = boardService.createBoard(boardForm, authentication);
        return "redirect:/board/" + boardId;
    }

    /* @GetMapping(value = "/{id}")
    public String displayPartyDetailPage(@PathVariable("id") String id, Model model) {
        model.addAttribute("detail", boardService.findBoardDetailById(Long.parseLong(id)));
        return "party/board_detail";

     */
}

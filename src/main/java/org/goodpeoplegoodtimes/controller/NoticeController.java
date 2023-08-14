package org.goodpeoplegoodtimes.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Notice;
import org.goodpeoplegoodtimes.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping(value = "/notice_list")
    public String displayNoticeList(Model model) {
        List<Notice> noticeList = this.noticeService.getList();
        model.addAttribute("noticeList", noticeList);
        return "notice/notice_list";
    }

    @GetMapping("/notice_form")
    public String displayNoticeCreationForm() {
        return "notice/notice_form";
    }
}

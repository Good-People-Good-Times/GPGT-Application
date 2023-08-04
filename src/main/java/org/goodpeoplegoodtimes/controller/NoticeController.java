package org.goodpeoplegoodtimes.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Notice;
import org.goodpeoplegoodtimes.dto.NoticeDto;
import org.goodpeoplegoodtimes.repository.NoticeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping(value = "/list")
    public String notice(Model model) {
        List<Notice> noticeList = this.noticeRepository.findAll();
        model.addAttribute("NoticeDto", new NoticeDto());
        return "noticelist";
    }

    @GetMapping("/form")
    public String noticeCreate() {
        return "notice_form";
    }

}

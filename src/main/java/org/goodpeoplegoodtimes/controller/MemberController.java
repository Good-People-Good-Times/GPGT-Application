package org.goodpeoplegoodtimes.controller;


import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/profile")
    public String getProfile(Authentication authentication, Model model) {
        model.addAttribute("profile", memberService.getMyProfile(authentication.getName()));
        return "프로필페이지";
    }

}

package org.goodpeoplegoodtimes.controller;
import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/profile")
    public String getProfile() {
        return "profile/profile";
    }

    @GetMapping(value = "/profile/{memberId}")
    public String getOtherProfile(@PathVariable(value = "memberId") Long memberId, Model model) {
        model.addAttribute("other_profile", memberService.getProfile(memberId));
        return "profile/other_profile";
    }
}
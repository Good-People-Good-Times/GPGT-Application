package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/profile")
public class ProfileController {

    private final MemberService memberService;

    @GetMapping(value = "/my_profile")
    public String myProfile() {
        return "profile/my_profile";
    }

    @GetMapping(value = "/your_profile")
    public String yourProfile() {
        return "profile/your_profile";
    }

}
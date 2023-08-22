package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.goodpeoplegoodtimes.service.PartyService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/profile")

public class ProfileController {

    private final MemberService memberService;
    private final PartyService partyService;

    @GetMapping(value = "/my_profile")
    public String displayMyProfile(@RequestParam(value = "cond", required = false) String cond,
                                   Authentication authentication, Model model) {
        model.addAttribute("myPartyList", partyService.getMyPartyList(authentication.getName(), cond));
        return "profile/my_profile";
    }


    @GetMapping(value = "/your_profile")
    public String displayOtherUserProfile(@RequestParam(value = "cond", required = false) String cond,
                                          Authentication authentication, Model model) {
        model.addAttribute("myPartyList", partyService.getMyPartyList(authentication.getName(), cond));
        return "profile/your_profile";
    }

}

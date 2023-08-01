package org.goodpeoplegoodtimes.controller;


import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.dto.SignupDto;
import org.goodpeoplegoodtimes.exception.EmailDuplicationException;
import org.goodpeoplegoodtimes.exception.NicknameDuplicationException;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("SignupDto", new SignupDto());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signup(SignupDto signupDto) {

/*        // 회원가입
        try {
            memberService.signup(signupDto);
        } catch (EmailDuplicationException e) {
            return "redirect:/members/login";
        } catch (NicknameDuplicationException e) {
            return "redirect:/members/signup";
        }*/

        return "redirect:/members/login";
    }
    @GetMapping(value = "/pw")
    public String findPassword() {
        return "findPW";
    }

    @GetMapping(value = "/email")
    public String findEmail() {
        return "findEmail";
    }

    @GetMapping(value = "/resetpw")
    public String resetPassword() {
        return "resetPW";
    }

    @GetMapping(value = "/party")
    public String newParty() {
        return "newParty";
    }

    @GetMapping("/notice")
    public String noticeCreate(){
        return "notice_form";
    }
}

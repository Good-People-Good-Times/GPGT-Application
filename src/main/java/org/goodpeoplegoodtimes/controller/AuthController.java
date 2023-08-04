package org.goodpeoplegoodtimes.controller;


import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.dto.SignupForm;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final MemberService memberService;


    @GetMapping(value = "/login")
    public String loginPage() {
        return "auth/login_page";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("form", new SignupForm());
        return "auth/signup_page";
    }

    @PostMapping(value = "/signup")
    public String signup(@Valid SignupForm signupForm,
                         BindingResult bindingResult,
                         Model model) {

        // 유효성 검사.
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("form", signupForm);
            return "auth/signup_page";
        }

        memberService.save(signupForm);
        return "redirect:/auth/login";
    }

    @GetMapping(value = "/find/email")
    public String findEmail() {
        return "auth/find/find_email_page";
    }

    @GetMapping(value = "/reset/password")
    public String resetPassword() {
        return "auth/reset/reset_pw_page";
    }

}

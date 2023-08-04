package org.goodpeoplegoodtimes.controller;


import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.dto.SignupDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping(value = "/login")
    public String loginPage() {
        return "auth/login_page";
    }

    @GetMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("SignupDto", new SignupDto());
        return "auth/signup_page";
    }

    @PostMapping(value = "/signup")
    public String signup(SignupDto signupDto) {
        return "redirect:/members/login";
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

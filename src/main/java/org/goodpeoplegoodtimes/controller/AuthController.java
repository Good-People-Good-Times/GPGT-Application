package org.goodpeoplegoodtimes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.dto.auth.SignupForm;
import org.goodpeoplegoodtimes.exception.member.EmailAlreadyExistsException;
import org.goodpeoplegoodtimes.exception.member.NicknameAlreadyExistsException;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final MemberService memberService;

    @GetMapping(value = "/login")
    public String displayLoginPage(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "이메일 혹은 비밀번호가 잘못되었습니다.");
        }
        return "auth/login_page";
    }


    @GetMapping(value = "/signup")
    public String displaySignupForm(Model model) {
        model.addAttribute("form", new SignupForm());
        return "auth/signup_page";
    }

    @GetMapping(value = "/signup/check")
    public String checkEmailDuplication(@RequestParam("email") String email, Model model) {

        if (!memberService.validateEmailDuplication(email)) {
            SignupForm signupForm = new SignupForm();
            signupForm.setEmail(email);

            model.addAttribute("emailCheckRes", "해당 이메일을 사용할 수 있습니다.");
            model.addAttribute("form", signupForm);
            return "auth/signup_page";
        }

        model.addAttribute("emailCheckRes", "해당 이메일을 사용할 수 없습니다.");
        return "auth/signup_page";
    }

    @PostMapping(value = "/signup")
    public String submitSignup(@Valid SignupForm signupForm,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("form", signupForm);
            return "auth/signup_page";
        }

        try {
            memberService.save(signupForm);
        } catch (EmailAlreadyExistsException e ) {
            model.addAttribute("emailDupError", "이미 존재하는 이메일입니다.");
            model.addAttribute("form", signupForm);
            return "auth/signup_page";
        } catch (NicknameAlreadyExistsException e) {
            model.addAttribute("nickNameDupError", "이미 존재하는 닉네임입니다.");
            model.addAttribute("form", signupForm);
            return "auth/signup_page";
        }

        return "redirect:/auth/login";
    }

    @GetMapping(value = "/find/email")
    public String displayEmailFindingForm() {
        return "auth/find/find_email_page";
    }

    @GetMapping(value = "/reset/password")
    public String displayResetPasswordForm() {
        return "auth/reset/reset_pw_page";
    }

}

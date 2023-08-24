package org.goodpeoplegoodtimes.controller.advice;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.dto.member.ProfileResponseDto;
import org.goodpeoplegoodtimes.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomControllerAdvice {

    private final MemberService memberService;

    @ModelAttribute("profile")
    public ProfileResponseDto getProfile(Authentication authentication) {
        if (authentication == null) return null;
        return memberService.getProfile(authentication.getName());
    }
}
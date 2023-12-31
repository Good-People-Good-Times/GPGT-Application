package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.dto.SignupForm;
import org.goodpeoplegoodtimes.exception.EmailAlreadyExistsException;
import org.goodpeoplegoodtimes.exception.NicknameAlreadyExistsException;
import org.goodpeoplegoodtimes.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(SignupForm signupForm) {
        validateEmailDuplication(signupForm.getEmail());
        validateNicknameDuplication(signupForm.getNickname());
        return memberRepository.save(Member.of(signupForm)).getId();
    }

    @Transactional(readOnly = true)
    public void validateEmailDuplication(String email) {
        if (memberRepository.existsByEmail(email))
            throw new EmailAlreadyExistsException(email + " is already registered.");
    }

    @Transactional(readOnly = true)
    public void validateNicknameDuplication(String nickname) {
        if (memberRepository.existsByNickname(nickname))
            throw new NicknameAlreadyExistsException(nickname + " is already in use.");
    }

}

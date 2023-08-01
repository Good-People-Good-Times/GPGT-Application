package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.dto.SignupDto;
import org.goodpeoplegoodtimes.exception.EmailDuplicationException;
import org.goodpeoplegoodtimes.exception.NicknameDuplicationException;
import org.goodpeoplegoodtimes.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long signup(SignupDto signupDto) {
        emailDuplicationValidate(signupDto.getEmail());
        nicknameDuplicationValidate(signupDto.getNickname());
        return memberRepository.save(Member.of(signupDto)).getId();
    }

    @Transactional(readOnly = true)
    public void emailDuplicationValidate(String email) {
        if (memberRepository.existsByEmail(email))
            throw new EmailDuplicationException(email + "은 이미 존재하는 이메일입니다.");
    }

    @Transactional(readOnly = true)
    public void nicknameDuplicationValidate(String nickname) {
        if (memberRepository.existsByNickname(nickname))
            throw new NicknameDuplicationException(nickname + "은 이미 존재하는 이메일입니다.");
    }
}

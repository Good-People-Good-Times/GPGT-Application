package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goodpeoplegoodtimes.domain.Member;
import org.goodpeoplegoodtimes.domain.dto.auth.SignupForm;
import org.goodpeoplegoodtimes.exception.member.EmailAlreadyExistsException;
import org.goodpeoplegoodtimes.exception.member.NicknameAlreadyExistsException;
import org.goodpeoplegoodtimes.exception.member.EmailNotFoundException;
import org.goodpeoplegoodtimes.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public Long save(SignupForm signupForm) {
        validateEmailDuplication(signupForm.getEmail());
        validateNicknameDuplication(signupForm.getNickname());
        return memberRepository.save(Member.of(signupForm, passwordEncoder.encode(signupForm.getPassword()))).getId();
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

    @Transactional(readOnly = true)
    public Member getMember(String email) throws EmailNotFoundException {
        return memberRepository.findByEmail(email).orElseThrow(
            () -> new EmailNotFoundException("해당 이메일의 유저는 존재하지 않습니다.")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = getMember(email);
        return User.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .authorities(new SimpleGrantedAuthority(member.getRole().getValue()))
            .build();
    }

}

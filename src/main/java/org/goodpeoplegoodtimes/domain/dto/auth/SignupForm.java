package org.goodpeoplegoodtimes.domain.dto.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {

    @Email(message = "이메일 형식을 올바르게 입력해 주세요.")
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @Size(min = 8, message = "비밀번호는 최소 8자 이상으로 입력해주세요.")
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @Size(min = 2, message = "닉네임은 최소 2자 이상으로 입력해주세요.")
    @NotEmpty(message = "닉네임을 입려해주세요.")
    private String nickname;

}

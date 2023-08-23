package org.goodpeoplegoodtimes.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.goodpeoplegoodtimes.domain.constant.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private int imgNum;
    private Role role;
}

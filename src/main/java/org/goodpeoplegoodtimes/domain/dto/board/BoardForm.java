package org.goodpeoplegoodtimes.domain.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardForm {

    @Size(min = 5, max = 50, message = "제목은 5글자 이상 50글자 내로 작성해주세요.")
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @Size(max = 500, message = "내용은 500글자 내로 작성해주세요.")
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

}

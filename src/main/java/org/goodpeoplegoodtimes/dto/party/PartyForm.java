package org.goodpeoplegoodtimes.dto.party;

import lombok.Data;
import org.goodpeoplegoodtimes.domain.constant.Category;

@Data
public class PartyForm {

    private String title;
    private Category category;
    private String content;

}

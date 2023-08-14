package org.goodpeoplegoodtimes.domain.constant;

import lombok.Getter;

@Getter
public enum PartyStatus {
    RECRUITING("모집중."),
    CLOSE("마감.");

    private String value;

    PartyStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

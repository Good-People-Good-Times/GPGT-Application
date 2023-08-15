package org.goodpeoplegoodtimes.domain.constant;

import lombok.Getter;

@Getter
public enum Category {

    TAXI("택시파티"),
    MEAL("식사파티"),
    CAFE("카페파티"),
    MOVIE("영화파티"),
    EXERCISE("운동파티"),
    HOBBY("취미파티"),
    ETC("기타");

    private String value;

    Category(String value) {
        this.value = value;
    }

}

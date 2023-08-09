package org.goodpeoplegoodtimes.domain.constant;

import lombok.Getter;

@Getter
public enum Category {

    TAXI("택시팟"),
    MEAL("밥팟"),
    CAFE("카페팟"),
    MOVIE("영화팟"),
    EXERCISE("운동팟"),
    HOBBY("취미팟"),
    ETC("기타");

    private String value;

    Category(String value) {
        this.value = value;
    }

}

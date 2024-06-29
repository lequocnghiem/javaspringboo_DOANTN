package com.api.thuctaptotnghiepbackend.chat.entity;

import lombok.Getter;

@Getter
public enum EMessageStatus {
    RECEIVED("received"),
    DELIVERED("delivered");

    private final String value;

    EMessageStatus(String value) {
        this.value = value;
    }
}

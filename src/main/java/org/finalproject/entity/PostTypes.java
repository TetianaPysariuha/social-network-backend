package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PostTypes {
    POST("POST"),
    COMMENT("COMMENT");
    private String value;

    PostTypes(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}

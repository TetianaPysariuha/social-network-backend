package org.finalproject.utilites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.EnumUtils;

public enum FriendStatus {
    pending("pending"),
    accepted("accepted"),
    rejected("rejected"),
    unfriended("unfriended");

    private final String val;

    FriendStatus(String val) {
        this.val = val;
    }

    @JsonValue
    public String getValue() {
            return val;
        }

    @JsonCreator
    public static FriendStatus forValue(String name) {
        return EnumUtils.getEnumMap(FriendStatus.class).get(name);
    }
}

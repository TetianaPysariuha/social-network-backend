package org.finalproject.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.EnumUtils;



    public enum NotificationType {
        newLike("newLike"),
        newComment("newComment"),
        newRepost("newRepost"),
        newMessage("newMessage"),
        editMessage("editMessage"),
        friendsBirthday("friendsBirthday");

        private final String val;

        NotificationType(String val) {
            this.val = val;
        }

        @JsonValue
        public String getValue() {
            return val;
        }

        @JsonCreator
        public static org.finalproject.util.NotificationType forValue(String name) {
            return EnumUtils.getEnumMap(org.finalproject.util.NotificationType.class).get(name);
        }
    }



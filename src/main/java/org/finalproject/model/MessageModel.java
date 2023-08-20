package org.finalproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MessageModel {

    private String message;
    private String routingKey;
}

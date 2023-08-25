package org.finalproject.jwt;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class RegisterRequest {

    private String email;
    private String password;

    private String name;

    private String surname;

    private String gender;

    private String month;

    private String year;

    private String day;

}

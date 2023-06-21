package org.finalproject.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRequestDto {
    private String fullName;

    private String email;
    private String password;

    private Date birthDate;

    private String country;

    private String  city;
    private String  gender;

    private String workPlace;

    private String profilePicture;

    private String about;


    private String profileBackgroundPicture;
}

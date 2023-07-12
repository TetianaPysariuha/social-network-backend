package org.finalproject.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class UserDto {
    private Long id;

    private String fullName;

    private String email;

    private Date birthDate;

    private String country;

    private String  city;
    private String  gender;

    private String workPlace;

    private String profilePicture;

    private String about;


    private String profileBackgroundPicture;

}

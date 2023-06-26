package org.finalproject.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRequestDto {
    @NotNull
    @Min(1)
    @Max(1000)
    private  Long id;
    @NotNull
    @Size(min = 4, message = "user name should have at least 4 characters")
    private String fullName;
   @Email
    private String email;
    private String password;

    private Date birthDate;

    private String country;
    @NotNull
    @Size(min = 2, message = "city name should have at least 2 characters")
    private String  city;
    private String  gender;

    private String workPlace;

    private String profilePicture;
    @NotNull
    @Size(min = 2,max = 350,message = "user description should have at least 2 characters and not more than 350 characters")
    private String about;


    private String profileBackgroundPicture;
}

package org.finalproject.dto;

import lombok.*;
import org.finalproject.entity.UserImage;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {

    private Long id;

    private String fullName;

    private String email;

    private Date birthDate;

    private String country;

    private String city;
    private String gender;

    private String workPlace;

    private String profilePicture;

    private String about;

    private String profileBackgroundPicture;

    private List<UserImageDto> userImages;


}

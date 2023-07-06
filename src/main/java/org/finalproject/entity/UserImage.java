package org.finalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter
@Entity
@Table(name = "user_images")
public class UserImage {

    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "user_id")
    private Long userId;
}

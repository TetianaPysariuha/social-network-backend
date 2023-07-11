package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_images")

public class UserImage extends BaseEntity {

    @Column(name = "img_url")
    private String imageUrl;
    @Column(name = "user_id")

    private Long userId;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE } ,fetch = FetchType.EAGER )
    @JoinColumn(name = "image_user_id")
    User user;

}

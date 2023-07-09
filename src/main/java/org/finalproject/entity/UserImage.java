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
    @ManyToOne(cascade = {CascadeType.MERGE } ,fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id")
     User user;

}

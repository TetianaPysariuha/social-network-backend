package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id")

    User user;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER )
    @JoinColumn(name = "post_id")
    @JsonIgnore
    Post post;
}

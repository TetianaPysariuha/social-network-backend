package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.finalproject.util.FriendStatus;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "friends")
@ToString
public class Friend extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "friend_id")
    private  User friend;
    /*cascade = {CascadeType.MERGE } */
}

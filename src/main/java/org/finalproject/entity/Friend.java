package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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

    private String status;
    @ManyToOne(cascade = {CascadeType.MERGE } ,fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE } ,fetch = FetchType.EAGER )
    @JoinColumn(name = "friend_id")
    private  User friend;


}

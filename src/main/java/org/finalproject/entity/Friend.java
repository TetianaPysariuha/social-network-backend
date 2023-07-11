package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "friends")
@ToString

public class Friend extends BaseEntity {

    private String status;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE } ,fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id")
    @LazyCollection(LazyCollectionOption.FALSE)

    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE } ,fetch = FetchType.EAGER )
    @JoinColumn(name = "friend_id")
    @LazyCollection(LazyCollectionOption.FALSE)

    private  User friend;


}

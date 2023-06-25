package org.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat extends BaseEntity {

    @OneToMany(cascade ={CascadeType.MERGE, CascadeType.REMOVE })
    @JoinColumn(name = "message_id")
    private List<Message> messages;

    @OneToMany(cascade ={CascadeType.MERGE, CascadeType.REMOVE })
    @JoinColumn(name = "messageImage_id")
    private List<MessageImage> messageImages;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> users;
}

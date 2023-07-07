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

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "chat")
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "chat")
    private List<MessageImage> messageImages;

    @ManyToMany(mappedBy = "chats", fetch = FetchType.EAGER)
    private List<User> users;

    public Chat(List<User> user) {

        this.users = user;
    }
}

package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "chat")
    //@JsonManagedReference
    private List<Message> messages;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "chat")
    @JsonIgnore
    private List<MessageImage> messageImages;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "chats", fetch = FetchType.EAGER)
    private List<User> users;

    public Chat(List<User> userList, List<Message> messageList) {

        this.users = userList;
        this.messages = messageList;
    }
}

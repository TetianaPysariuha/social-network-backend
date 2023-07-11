package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE},fetch = FetchType.EAGER,mappedBy = "chat")
    private List<MessageImage> messageImages = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "chats", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public Chat(List<User> user) {

        this.users = user;
    }
}

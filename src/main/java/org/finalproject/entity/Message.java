package org.finalproject.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "images"})
@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    @Column(name = "content")
    private String content;
    @Column(name = "chat_entity_id")
    private Long chatId;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User sender;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "messageId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MessageImage> images;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "readMessages", fetch = FetchType.EAGER)
    private List<User> user;

    public Message(String content, User sender, Chat chat, Long chatId) {

        this.content = content;
        this.sender = sender;
        this.chat = chat;
        this.chatId = chatId;
    }
}

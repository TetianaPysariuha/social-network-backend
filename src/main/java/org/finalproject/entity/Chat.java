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

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "chatId")
    @JsonIgnore
 //   @JoinColumn(name = "message_id")
    private List<Message> messages;
//Связь  не замкнута
  //  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
  //  @JoinColumn(name = "messageImage_id")
  //  private List<MessageImage> messageImages;

    @ManyToMany(mappedBy = "chats", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;

    public Chat(List<User> user) {

        this.users = user;
    }
}

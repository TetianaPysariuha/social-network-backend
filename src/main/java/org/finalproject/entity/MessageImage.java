package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter
@Entity
@Table(name = "message_images")
public class MessageImage extends BaseEntity {

    @Column(name = "img_url")
    private String imgUrl;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "message_id")
    @JsonIgnore
    private Message messageId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "chat_id")
    @JsonIgnore
    private Chat chat;

}

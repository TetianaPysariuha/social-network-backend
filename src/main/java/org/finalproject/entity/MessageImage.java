package org.finalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    @Column(name = "message_id")
    private Long messageId;

}

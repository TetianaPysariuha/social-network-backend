package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "comments")
public class ImgComment extends BaseEntity {


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "author_id")

    private User author;


    private String content;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "image_id")

    private UserImage image;



}

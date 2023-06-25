package org.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "post_type")
    private PostTypes postType;
    private String content;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Post parentId;


}

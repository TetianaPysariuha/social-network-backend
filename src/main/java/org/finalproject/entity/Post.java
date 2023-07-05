package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String postType;
    private String content;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    private Post parentId;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "likedPosts")


    private Set<User> likes=new HashSet<>() ;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "reposts")
    @JsonIgnore

    private Set<User> reposts=new HashSet<>();
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<PostImage> postImages ;



}

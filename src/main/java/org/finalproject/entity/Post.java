package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonIgnore
    private Post parentId;

  @OneToMany(cascade = {CascadeType.MERGE },fetch = FetchType.EAGER,mappedBy = "parentId")
    private List <Post> comments = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.MERGE },fetch = FetchType.EAGER,mappedBy = "likedPosts")
    private List<User> likes;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "reposts")
    @JsonIgnore

    private Set<User> reposts =new HashSet<>();
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<PostImage> postImages ;



}

package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "user", "content"})
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "posts")
public class Post extends BaseEntity {

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

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "parentId")
    @Where(clause = "post_type = 'comment'")
    private List<Post> comments = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "likedPosts")
    private List<User> likes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "reposts")
    @JsonIgnore

    private Set<User> repostsUsers = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<PostImage> postImages;

    @OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "parentId")
    @Where(clause = "post_type = 'post'")
    private List<Post> reposts = new ArrayList<>();


    public Post(User user, String postType, String content, Post parentId) {

        this.user = user;
        this.postType = postType;
        this.content = content;
        this.parentId = parentId;
    }

    public boolean addLike(User user) {

        if (user == null) {
            return false;
        }
        try {
            this.likes.add(user);
            user.getLikedPosts().add(this);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean removeLike(User user) {

        if (user == null) {
            return false;
        }
        try {
            this.likes.remove(user);
            user.getLikedPosts().remove(this);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}

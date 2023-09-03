package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "email", "fullName"})
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;

    private String email;
    @JsonIgnore
    private String password;
    @Column(name = "birth_date")
    private Date birthDate;

    private String country;
    private String  city;
    private String  gender;
    @Column(name = "work_place")
    private String workPlace;
    @Column(name = "profile_picture")
    private String profilePicture;
    private String about;
    @Column(name = "activation_code")
    @JsonIgnore
    private String activationCode = UUID.randomUUID().toString();
    @JsonIgnore
    private boolean activated ;

    @Column(name = "profile_background_picture")
    private String profileBackgroundPicture;

    @OneToMany (fetch = FetchType.EAGER ,mappedBy = "friend")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Friend> users;

    @OneToMany (fetch = FetchType.EAGER ,mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Friend> friends;
    @OneToMany (cascade = CascadeType.REMOVE ,fetch = FetchType.EAGER ,mappedBy = "user")
    @JsonIgnore
    private List<Post> posts ;
    @OneToMany (cascade = CascadeType.REMOVE ,fetch = FetchType.EAGER ,mappedBy = "sender")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Message> messages;

    @ManyToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.EAGER )
    @JsonIgnore
    @JoinTable(
            name = "users_chats",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "chat_id") })
    private List<Chat> chats;

    @ManyToMany(fetch = FetchType.EAGER )
    @JsonIgnore
    @JoinTable(
            name = "users_liked_posts",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") })
    private List<Post> likedPosts;
    @ManyToMany(fetch = FetchType.EAGER )
    @JsonIgnore
    @JoinTable(
            name = "users_reposted_posts",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") })
    private Set<Post> reposts = new HashSet<>();

    @OneToMany (cascade = CascadeType.REMOVE ,fetch = FetchType.EAGER ,mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<UserImage> userImages;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private List<Message> readMessages;


    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", about='" + about + '\'' +
                ", profileBackgroundPicture='" + profileBackgroundPicture + '\'' +
                '}';
    }
}


package org.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
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
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;

    private String email;
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

    @Column(name = "profile_background_picture")
    private String profileBackgroundPicture;

    @OneToMany (cascade = {CascadeType.MERGE },fetch = FetchType.EAGER ,mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Friend> users;

    @OneToMany (cascade = {CascadeType.MERGE},fetch = FetchType.EAGER ,mappedBy = "friend")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List<Friend> friends;
    @OneToMany (cascade = {CascadeType.MERGE,CascadeType.REMOVE },fetch = FetchType.EAGER ,mappedBy = "user")
    @JsonIgnore
    private List<Post> posts ;
    @OneToMany (cascade = {CascadeType.MERGE,CascadeType.REMOVE },fetch = FetchType.EAGER ,mappedBy = "sender")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Message> messages;

    @ManyToMany(cascade = { CascadeType.MERGE },fetch = FetchType.EAGER )
    @JsonIgnore
    @JoinTable(
            name = "users_chats",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "chat_id") })
    private List<Chat> chats;

    @ManyToMany(cascade = { CascadeType.MERGE },fetch = FetchType.EAGER )
    @JsonIgnore
    @JoinTable(
            name = "users_liked_posts",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") })
    private Set<Post> likedPosts=new HashSet<>();
    @ManyToMany(cascade = { CascadeType.MERGE },fetch = FetchType.EAGER )
    @JsonIgnore
    @JoinTable(
            name = "users_reposts",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") })
    private Set<Post> reposts=new HashSet<>();


    @Override
    public String toString() {
        return "User{" +
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


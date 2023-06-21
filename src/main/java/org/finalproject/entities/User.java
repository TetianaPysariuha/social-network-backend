package org.finalproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name ="full_name")
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
    @Column(name="profile_picture")
    private String profilePicture;

    private String about;

    @Column(name = "profile_background_picture")
    private String profileBackgroundPicture;

    @OneToMany (cascade = {CascadeType.MERGE },fetch = FetchType.EAGER ,mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List <Friend> users;

    @OneToMany (cascade = {CascadeType.MERGE},fetch = FetchType.EAGER ,mappedBy = "friend")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    List <Friend> friends;
    @OneToMany (cascade = {CascadeType.MERGE,CascadeType.REMOVE },fetch = FetchType.EAGER ,mappedBy = "user")
    @JsonIgnore
    private List<Post> posts ;
    @OneToMany (cascade = {CascadeType.MERGE,CascadeType.REMOVE },fetch = FetchType.EAGER ,mappedBy = "sender")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Message> messages;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "participants",cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JsonIgnore
    private List <Chat> chats;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JsonIgnore
    private List <Like> likes;

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


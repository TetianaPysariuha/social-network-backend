package org.finalproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.finalproject.util.FriendStatus;
import org.finalproject.util.NotificationStatus;
import org.finalproject.util.NotificationType;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(name="sender_full_name")
    private String senderFullName;


    private String content;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "receiver_id")

    private User receiver;



}

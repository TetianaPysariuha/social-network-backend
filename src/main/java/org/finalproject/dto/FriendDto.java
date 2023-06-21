package org.finalproject.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.finalproject.entities.User;

public class FriendDto {

    private Long id;
    private String status;

    private  User friend;

}

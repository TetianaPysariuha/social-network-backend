package org.finalproject.dto;

import org.finalproject.entity.Friend;
import org.finalproject.facade.GeneralFacade;
import org.springframework.stereotype.Service;

@Service
public class FriendDtoMapper extends GeneralFacade<Friend, FriendRequestDto,FriendDto> {

}


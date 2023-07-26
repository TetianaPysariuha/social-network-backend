package org.finalproject.dto;

import org.finalproject.entity.Friend;
import org.finalproject.facade.GeneralFacade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendDtoMapper extends GeneralFacade<Friend, FriendRequestDto,FriendDto> {

    @Autowired
    private ModelMapper mm;

    public FriendFullDto convertToFullDto(Friend entity) {
        return mm.map(entity, FriendFullDto.class);
    }
}


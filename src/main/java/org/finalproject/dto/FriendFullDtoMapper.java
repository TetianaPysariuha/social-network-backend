package org.finalproject.dto;

import org.finalproject.entity.Friend;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.service.DefaultFriendService;
import org.finalproject.utilites.FriendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendFullDtoMapper extends GeneralFacade<Friend, FriendRequestDto, FriendFullDto> {

    @Autowired
    private DefaultFriendService defaultFriendService;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    protected void decorateEntity(Friend entity, FriendRequestDto dto) {
        entity.setId(dto.getId());
        entity.setUser(userJpaRepository.findEntityById(dto.getUserID()));
        entity.setFriend(userJpaRepository.findEntityById(dto.getFriendID()));
        entity.setStatus(FriendStatus.forValue(dto.getStatus()));
    }

    @Override
    protected void decorateDto(FriendFullDto dto, Friend entity) {
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus().getValue());
        dto.setFriend(entity.getFriend());
        dto.setMutualFriends(defaultFriendService.getMutualFriends(entity.getUser().getId(), entity.getFriend().getId()));
        dto.setUser(entity.getUser());
    }
}

package org.finalproject.dto.friend;

import org.finalproject.dto.UserDtoMapper;
import org.finalproject.entity.User;
import org.finalproject.facade.GeneralFacade;
import org.finalproject.repository.UserJpaRepository;
import org.finalproject.service.DefaultFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendSuggestionsDtoMapper extends GeneralFacade<User, FriendRequestDto, FriendSuggestionsDto> {
    @Autowired
    private DefaultFriendService defaultFriendService;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserDtoMapper userDtoMapper;

    @Override
    protected void decorateDto(FriendSuggestionsDto dto, User entity) {
        dto.setFriend(userDtoMapper.convertToDto(entity));
        dto.setMutualFriends(null);
    }

}

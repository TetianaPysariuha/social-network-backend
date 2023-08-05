package org.finalproject.dto;

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

    @Override
    protected void decorateDto(FriendSuggestionsDto dto, User entity) {
        dto.setFriend(entity);
        dto.setMutualFriends(null);
    }

}

package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.finalproject.entities.Friend;
import org.finalproject.entities.User;
import org.finalproject.repository.RepositoryInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class DefaultFriendService extends GeneralService <Friend> implements ServiceInterface <Friend>{
    private RepositoryInterface <Friend> friendRepositoryInterface;
}

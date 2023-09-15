package org.finalproject.service;


import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.finalproject.repository.FriendJpaRepository;
import org.finalproject.util.FriendStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultFriendServiceTest {

    @Mock
    private FriendJpaRepository friendTestJpaRepository;


    @InjectMocks
    private GeneralService<Friend> friendService  = new GeneralService<Friend>() {
    };

    @InjectMocks
    private DefaultFriendService defaultFriendService;

    @Captor
    private ArgumentCaptor<Friend> friendArgumentCaptor;

    @Test
    public void testGetAllPageble() {
        Friend friend = new Friend();
        when(friendTestJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(friend)));
        Pageable pageable = PageRequest.of(1, 2);
        List<Friend> friends = friendService.findAll(pageable).toList();

        assertEquals(friend, friends.get(0));
    }

    @Test
    public void test_GetAll_Success() {
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        List<Friend> friendsExpected = List.of(friend1, friend2);
        when(friendTestJpaRepository.findAll())
                .thenReturn(friendsExpected);

        List<Friend> friendsActual = friendService.findAll();
        assertNotNull(friendsActual);
        assertFalse(friendsActual.isEmpty());
        assertIterableEquals(friendsExpected, friendsActual);
    }

    @Test
    public void test_Create_Success() {
        Friend friend1 = new Friend();

        friendService.save(friend1);

        verify(friendTestJpaRepository).save(friendArgumentCaptor.capture());
        Friend friendActualArgument = friendArgumentCaptor.getValue();
        assertEquals(friend1,friendActualArgument);
    }
    @Test
    public void test_Put_Success() {
        Friend friend1 = new Friend() ;


        friend1.setStatus(FriendStatus.accepted);
        friend1.setCreatedDate(new Date());

        friendService.save(friend1);

        verify(friendTestJpaRepository).save(friendArgumentCaptor.capture());
        Friend  friendActualArgument = friendArgumentCaptor.getValue();
        assertEquals(friend1, friendActualArgument);
    }
    @Test
    public void test_Delete_Success() {
        Friend friend1= new Friend() ;

        friendService.save(friend1);
        friend1.setId(1L);

        friend1.setStatus(FriendStatus.accepted);
        friendService.delete(friend1);

        verify(friendTestJpaRepository).delete(friendArgumentCaptor.capture());
        Friend friendActualArgument = friendArgumentCaptor.getValue();
        assertEquals(friend1, friendActualArgument);
    }


}
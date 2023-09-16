package org.finalproject.service;


import org.finalproject.entity.User;
import org.finalproject.repository.UserJpaRepository;
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
public class DefaultUserServiceTest {

    @Mock
    private UserJpaRepository userTestJpaRepository;


    @InjectMocks
    private GeneralService<User> userService  = new GeneralService<User>() {
    };

    @InjectMocks
    private DefaultUserService defaultUserService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void testGetAllPageble() {
       User user = new User();
        when(userTestJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(user)));
        Pageable pageable = PageRequest.of(1, 2);
        List<User> users = userService.findAll(pageable).toList();

        assertEquals(user, users.get(0));
    }

    @Test
    public void test_GetAll_Success() {
     User user1 = new User();
        User user2 = new User();
        List<User> usersExpected = List.of(user1, user2);
        when(userTestJpaRepository.findAll())
                .thenReturn(usersExpected);

        List<User> usersActual = userService.findAll();
        assertNotNull(usersActual);
        assertFalse(usersActual.isEmpty());
        assertIterableEquals(usersExpected, usersActual);
    }

    @Test
    public void test_Create_Success() {
        User user1 = new User();

        userService.save(user1);

        verify(userTestJpaRepository).save(userArgumentCaptor.capture());
        User userActualArgument = userArgumentCaptor.getValue();
        assertEquals(user1,userActualArgument);
    }
    @Test
    public void test_Put_Success() {
        User user1 = new User() ;


        user1.setFullName("Jane Birkin");
        user1.setCreatedDate(new Date());
        when(userTestJpaRepository.getOne(user1.getId())
        )
                .thenReturn(user1);
        defaultUserService.update(user1);

        verify(userTestJpaRepository).save(userArgumentCaptor.capture());
        User  userActualArgument = userArgumentCaptor.getValue();
        assertEquals(user1, userActualArgument);
    }
    @Test
    public void test_Delete_Success() {
        User user1= new User() ;

       userService.save(user1);
        user1.setId(1L);

        user1.setFullName("Jane Birkin");
        userService.delete(user1);

        verify(userTestJpaRepository).delete(userArgumentCaptor.capture());
        User userActualArgument = userArgumentCaptor.getValue();
        assertEquals(user1, userActualArgument);
    }
    @Test
    public void test_GetById_Success() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
       User  userExpected = user2;
        when(userTestJpaRepository.getOne(user2.getId()))
                .thenReturn(user2);

        User userActual = defaultUserService.getOne(user2.getId());
        assertNotNull(userActual);

        assertEquals(userExpected, userActual);
    }

}
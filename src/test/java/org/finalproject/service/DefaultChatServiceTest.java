package org.finalproject.service;


import org.finalproject.entity.Chat;
import org.finalproject.entity.User;
import org.finalproject.repository.ChatRepository;
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
public class DefaultChatServiceTest {

    @Mock
    private ChatRepository chatTestJpaRepository;


    @InjectMocks
    private GeneralService<Chat> chatService  = new GeneralService<Chat>() {
    };

    @InjectMocks
    private DefaultChatService defaultChatService;

    @Captor
    private ArgumentCaptor<Chat> chatArgumentCaptor;

    @Test
    public void testGetAllPageble() {
        Chat chat = new Chat();
        when(chatTestJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(chat)));
        Pageable pageable = PageRequest.of(1, 2);
        List<Chat> chats = chatService.findAll(pageable).toList();

        assertEquals(chat, chats.get(0));
    }

    @Test
    public void test_GetAll_Success() {
        Chat chat1 = new Chat();
        Chat chat2 = new Chat();
        List<Chat> chatsExpected = List.of(chat1, chat2);
        when(chatTestJpaRepository.findAll())
                .thenReturn(chatsExpected);

        List<Chat> chatsActual = chatService.findAll();
        assertNotNull(chatsActual);
        assertFalse(chatsActual.isEmpty());
        assertIterableEquals(chatsExpected, chatsActual);
    }

    @Test
    public void test_Create_Success() {
        Chat chat1 = new Chat();

        chatService.save(chat1);

        verify(chatTestJpaRepository).save(chatArgumentCaptor.capture());
        Chat chatActualArgument = chatArgumentCaptor.getValue();
        assertEquals(chat1,chatActualArgument);
    }
    @Test
    public void test_Put_Success() {
        Chat chat1 = new Chat();


        chat1.setId(1L);
        chat1.setCreatedDate(new Date());

        defaultChatService.save(chat1);

        verify(chatTestJpaRepository).save(chatArgumentCaptor.capture());
        Chat chatActualArgument = chatArgumentCaptor.getValue();
        assertEquals(chat1,chatActualArgument);
    }
    @Test
    public void test_Delete_Success() {
        Chat chat1 = new Chat();

        chatService.save(chat1);
        chat1.setId(1L);


        chatService.delete(chat1);

        verify(chatTestJpaRepository).delete(chatArgumentCaptor.capture());
        Chat chatActualArgument = chatArgumentCaptor.getValue();
        assertEquals(chat1,chatActualArgument);
    }
    @Test
    public void test_GetById_Success() {
        Chat chat1 = new Chat();
        chat1.setId(1L);
        Chat chat2 = new Chat();
        chat2.setId(2L);
        Chat  chatExpected = chat2;
        when(chatTestJpaRepository.findEntityById(chat2.getId()))
                .thenReturn(chat2);

        Chat chatActual = defaultChatService.findEntityById(chat2.getId());
        assertNotNull(chatActual);

        assertEquals(chatExpected, chatActual);
    }

}
package org.finalproject.service;


import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.entity.Message;
import org.finalproject.entity.User;
import org.finalproject.repository.MessageRepository;
import org.finalproject.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DefaultMessageServiceTest {

    @Mock
    private MessageRepository messageTestJpaRepository;


    @InjectMocks
    private GeneralService<Message> messageService  = new GeneralService<Message>() {
    };


    @InjectMocks
    private DefaultMessageService defaultMessageService;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    public void testGetAllPageble() {
        Message message = new Message();
        when(messageTestJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(message)));
        Pageable pageable = PageRequest.of(1, 2);
        List<Message> messages = messageService.findAll(pageable).toList();

        assertEquals(message, messages.get(0));
    }

    @Test
    public void test_GetAll_Success() {
        Message message1 = new Message();
        Message message2 = new Message();
        List<Message> messagesExpected = List.of(message1, message2);
        when(messageTestJpaRepository.findAll())
                .thenReturn(messagesExpected);

        List<Message> messagesActual = messageService.findAll();
        assertNotNull(messagesActual);
        assertFalse(messagesActual.isEmpty());
        assertIterableEquals(messagesExpected, messagesActual);
    }

    @Test
    public void test_Create_Success() {
        Message message1 = new Message();

        messageService.save(message1);

        verify(messageTestJpaRepository).save(messageArgumentCaptor.capture());
        Message  messageActualArgument = messageArgumentCaptor.getValue();
        assertEquals(message1,messageActualArgument);
    }
    @Test
    public void test_Put_Success() {
        Message message1 = new Message();


        message1.setContent("Hy friend");
        message1.setId(1L);

        messageService.save(message1);

        verify(messageTestJpaRepository).save(messageArgumentCaptor.capture());
        Message  messageActualArgument = messageArgumentCaptor.getValue();
        assertEquals(message1, messageActualArgument);
    }
    @Test
    public void test_Delete_Success() {
        Message message1 = new Message();

        messageService.save(message1);
        message1.setId(1L);

        message1.setContent("Hy friend");
        messageService.delete(message1);

        verify(messageTestJpaRepository).delete(messageArgumentCaptor.capture());
        Message messageActualArgument = messageArgumentCaptor.getValue();
        assertEquals(message1, messageActualArgument);
    }


}
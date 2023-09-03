package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.dto.chat.MessageSearchDto;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.finalproject.entity.MessageSearchProjection;
import org.finalproject.entity.User;
import org.finalproject.repository.MessageRepository;
import org.finalproject.service.jwt.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMessageService extends GeneralService<Message> {

    private final MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GeneralService<Chat> chatService;
    @Autowired
    private GeneralService<Message> messageService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageDtoMapper messageDtoMapper;

    public List<MessageSearchDto> findByContent(String content) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        List<MessageSearchProjection> projections = messageRepository.findByContent(content, user.getId());
        return projections.stream()
                .map(MessageSearchDto::fromProjection)
                .collect(Collectors.toList());
    }

    public void createNewMessage(MessageDtoRequest messageDtoRequest) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        Chat chat = chatService.findEntityById(messageDtoRequest.getChatId());
        Message message = new Message(messageDtoRequest.getContent(), user, chat, chat.getId());
        messageService.save(message);
        List<User> users = chat.getUsers();
        List<Long> usersId = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usersId.add(users.get(i).getId());
        }
        usersId.removeIf(id -> id.equals(user.getId()));
        for (Long toUserId : usersId) {
            rabbitTemplate.convertAndSend("messages-exchange", "user." + toUserId, messageDtoRequest.getContent());
            rabbitTemplate.convertAndSend("notification-exchange", "user." + toUserId, messageDtoRequest);
        }
    }

    public void editMessage(MessageDtoRequest messageDtoRequest) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        Chat chat = chatService.findEntityById(messageDtoRequest.getChatId());
        Message messageEntity = messageDtoMapper.convertToEntity(messageDtoRequest);
        messageEntity.setSender(user);
        messageEntity.setChat(chat);
        messageEntity.setCreatedDate(messageService.getOne(messageEntity.getId()).getCreatedDate());
        messageEntity.setCreatedBy(messageService.getOne(messageEntity.getId()).getCreatedBy());
        messageService.save(messageEntity);
        List<User> users = chat.getUsers();
        List<Long> usersId = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usersId.add(users.get(i).getId());
        }
        usersId.removeIf(id -> id.equals(user.getId()));
        for (Long toUserId : usersId) {
            rabbitTemplate.convertAndSend("messages-exchange", "user." + toUserId, messageDtoRequest.getContent());
            rabbitTemplate.convertAndSend("notification-exchange", "user." + toUserId, messageDtoRequest);
        }
    }
}

package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.dto.chat.MessageSearchDto;
import org.finalproject.entity.*;
import org.finalproject.repository.MessageRepository;
import org.finalproject.service.jwt.UserService;
import org.finalproject.util.NotificationStatus;
import org.finalproject.util.NotificationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMessageService extends GeneralService<Message> {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DefaultMessageImageService defaultMessageImageService;
    @Autowired
    private UserService userService;
    @Autowired
    private GeneralService<Chat> chatService;
    @Autowired
    private GeneralService<Message> messageService;
    @Autowired
    private GeneralService<User> userGeneralService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageDtoMapper messageDtoMapper;
    @Autowired
    private GeneralService<Notification> notificationGeneralService;

    public List<MessageSearchDto> findByContent(String content) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        List<MessageSearchProjection> projections = messageRepository.findByContent(content, user.getId());
        return projections.stream()
                .map(MessageSearchDto::fromProjection)
                .collect(Collectors.toList());
    }

    public void createNewMessage(MessageDtoRequest messageDtoRequest, List<MultipartFile> multipartFiles) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        Chat chat = chatService.findEntityById(messageDtoRequest.getChatId());
        List<User> userList = chat.getUsers();
        userList.removeIf(u -> u.getId().equals(user.getId()));
        Message message = new Message(messageDtoRequest.getContent(), user, chat, chat.getId());
        Message savedMessage = messageService.save(message);
        for (User userFromChat : userList) {
            userFromChat.getReadMessages().add(savedMessage);
        }
        if (multipartFiles != null) {
            List<String> imageUrl;
            try {
                imageUrl = defaultMessageImageService.uploadImage(multipartFiles, savedMessage.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Message finalMessage = savedMessage;
            List<MessageImage> messageImages = imageUrl.stream()
                    .map(img -> new MessageImage(img, finalMessage, finalMessage.getChat()))
                    .collect(Collectors.toList());
            messageImages.forEach(defaultMessageImageService::save);
            finalMessage.setImages(messageImages);
            messageService.save(finalMessage);
        }
        List<User> users = chat.getUsers();
        users.removeIf(u -> u.getId().equals(user.getId()));
        List<Long> usersId = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usersId.add(users.get(i).getId());
        }
        Notification notification = new Notification(NotificationType.newMessage, NotificationStatus.pending, user, savedMessage.getContent(), savedMessage.getId(), users);
        //notification = notificationGeneralService.save(notification);
        for (Long toUserId : usersId) {
            rabbitTemplate.convertAndSend("messages-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
            rabbitTemplate.convertAndSend("notification-exchange", "user." + toUserId, notification);
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
        Message savedMessage = messageService.save(messageEntity);
        List<User> users = chat.getUsers();
        users.removeIf(u -> u.getId().equals(user.getId()));
        List<Long> usersId = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usersId.add(users.get(i).getId());
        }
        for (Long toUserId : usersId) {
            rabbitTemplate.convertAndSend("messages-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
            rabbitTemplate.convertAndSend("notification-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
        }
    }

    public Message getById(Long messageId) {

        return messageService.findEntityById(messageId);
    }

    public void deleteById(Long messageId) {

        Message message = messageService.findEntityById(messageId);
        List<User> users = message.getUser();
        for (User user : users) {
            user.getReadMessages().removeIf(msg -> msg.getId().equals(messageId));
        }
        messageService.delete(message);
    }

    public boolean findUnReadMessages(Long messageId) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        List<Long> unReadMessages = messageRepository.findUnReadMessages(user.getId(), messageId);
        if (unReadMessages.size() == 0) {
            return true;
        }
        return false;
    }


}

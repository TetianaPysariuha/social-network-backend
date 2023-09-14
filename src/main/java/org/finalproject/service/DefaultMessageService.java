package org.finalproject.service;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.dto.chat.MessageSearchDto;
import org.finalproject.entity.*;
import org.finalproject.repository.MessageRepository;
import org.finalproject.service.jwt.UserService;
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
    private  MessageRepository messageRepository;
    @Autowired
    private  DefaultMessageImageService defaultMessageImageService;
    @Autowired
    private  UserService userService;
    @Autowired
    private GeneralService<Chat> chatService;
    @Autowired
    private GeneralService<Message> messageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private  MessageDtoMapper messageDtoMapper;

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
        Message message = new Message(messageDtoRequest.getContent(), user, chat, chat.getId());
        Message savedMessage = messageService.save(message);
        if (!multipartFiles.isEmpty()) {
            List<String> imageUrl;
            try {
                imageUrl = defaultMessageImageService.uploadImage(multipartFiles, savedMessage.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Message finalMessage = savedMessage;
            List<MessageImage> messageImages = imageUrl.stream()
                    .map(img -> new MessageImage(img, finalMessage, finalMessage.getChat()))
                    .toList();
            messageImages.forEach(defaultMessageImageService::save);
            finalMessage.setImages(messageImages);
            messageService.save(finalMessage);
        }
        List<User> users = chat.getUsers();
        List<Long> usersId = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usersId.add(users.get(i).getId());
        }
        usersId.removeIf(id -> id.equals(user.getId()));
        for (Long toUserId : usersId) {
            rabbitTemplate.convertAndSend("messages-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
            rabbitTemplate.convertAndSend("notification-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
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
        List<Long> usersId = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            usersId.add(users.get(i).getId());
        }
        usersId.removeIf(id -> id.equals(user.getId()));
        for (Long toUserId : usersId) {
            rabbitTemplate.convertAndSend("messages-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
            rabbitTemplate.convertAndSend("notification-exchange", "user." + toUserId, messageDtoMapper.decorateDto(savedMessage));
        }
    }

    public Message getById(Long messageId) {

        return messageService.findEntityById(messageId);
    }

    public void deleteById(Long messageId) {

        /*Message message = messageService.findEntityById(messageId);*/
        /*List<User> users = message.getUser();
        for (User user : users) {
            user.getReadMessages().removeIf(msg -> msg.getId().equals(messageId));
        }*/
        /*List<Message> messages = message.getSender().getMessages();
        messages.removeIf(msg -> msg.getId().equals(messageId));*/

    }


}

package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.UserDto;
import org.finalproject.dto.chat.MessageDto;
import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultMessageService;
import org.finalproject.service.GeneralService;
import org.finalproject.service.jwt.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageRestController {

    private final GeneralService<Message> messageService;
    private final GeneralService<Chat> chatService;
    private final MessageDtoMapper messageDtoMapper;

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final DefaultMessageService defaultMessageService;

    @GetMapping
    public List<MessageDto> getAll() {

        List<Message> messageList = messageService.findAll();
        return messageList.stream()
                .map(messageDtoMapper::decorateDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "updatedDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page messages = messageService.findAll(pageable);
        List<Message> messageList = messages.toList();
        List<MessageDto> messageDtoList = messageList.stream()
                .map(messageDtoMapper::decorateDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        Message message = messageService.findEntityById(id);
        if (message == null) {
            return ResponseEntity.badRequest().body("Message not found");
        }
        return ResponseEntity.ok().body(messageDtoMapper.decorateDto(message));
    }

//    @DeleteMapping
//    public ResponseEntity<?> deleteMessage(@RequestBody MessageDtoRequest messageDtoRequest) {
//
//        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        try {
//            User user = userService.getByEmail(auth).get();
//            messageDtoRequest.setId(user.getId());
//            messageService.delete(messageDtoMapper.convertToEntity(messageDtoRequest));
//            return ResponseEntity.ok().build();
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long chatId) {

        try {
            messageService.deleteById(chatId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @MessageMapping("/send")
    public ResponseEntity<?> createMessage(@RequestBody MessageDtoRequest messageDtoRequest) throws IOException {

        try {
            String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            User user = userService.getByEmail(auth).get();
            Chat chat = chatService.findEntityById(messageDtoRequest.getChatId());
            Message message = new Message(messageDtoRequest.getContent(), user, chat);
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
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @MessageMapping("/update")
    public ResponseEntity<?> update(@RequestBody MessageDtoRequest messageDtoRequest) throws IOException {

        try {
            String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            User user = userService.getByEmail(auth).get();
            Message messageEntity = messageDtoMapper.convertToEntity(messageDtoRequest);
            messageEntity.setSender(user);
            messageEntity.setCreatedDate(messageService.getOne(messageEntity.getId()).getCreatedDate());
            messageEntity.setCreatedBy(messageService.getOne(messageEntity.getId()).getCreatedBy());
            messageService.save(messageEntity);
            Chat chat = chatService.findEntityById(messageDtoRequest.getChatId());
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
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByContent")
    public ResponseEntity<?> findByContent(@RequestParam String partOfMsg) {

        try {
            String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            User user = userService.getByEmail(auth).get();
            return ResponseEntity.ok(defaultMessageService.findByContent(partOfMsg, user.getId()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping
//    readmessages(List newreadmessages)

}

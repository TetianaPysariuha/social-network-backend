package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.MessageDto;
import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<?> createNewMessage(@RequestBody MessageDtoRequest messageDtoRequest) throws IOException {

        try {
            defaultMessageService.createNewMessage(messageDtoRequest);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @MessageMapping("/update")
    public ResponseEntity<?> editMessage(@RequestBody MessageDtoRequest messageDtoRequest) throws IOException {

        try {
            defaultMessageService.editMessage(messageDtoRequest);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findByContent")
    public ResponseEntity<?> findByContent(@RequestBody MessageDtoRequest messageDtoRequest) {

        try {
            return ResponseEntity.ok(defaultMessageService.findByContent(messageDtoRequest.getContent()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping
//    readmessages(List newreadmessages)

}

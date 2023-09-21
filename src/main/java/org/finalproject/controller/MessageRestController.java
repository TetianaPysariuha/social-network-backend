package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.MessageDto;
import org.finalproject.dto.chat.MessageDtoMapper;
import org.finalproject.dto.chat.MessageDtoRequest;
import org.finalproject.dto.chat.MessageSearchDto;
import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.finalproject.entity.User;
import org.finalproject.service.DefaultMessageService;
import org.finalproject.service.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
@Transactional
public class MessageRestController {

    private final MessageDtoMapper messageDtoMapper;
    private final DefaultMessageService defaultMessageService;
    private final GeneralService<Message> messageGeneralService;
    private final GeneralService<Chat> chatGeneralService;
    private final GeneralService<User> userGeneralService;

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getById(@PathVariable("id") Long messageId) {

        MessageDto messageDto = messageDtoMapper.decorateDto(defaultMessageService.findEntityById(messageId));
        if (messageDto == null) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(messageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long messageId) {

        defaultMessageService.deleteById(messageId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @MessageMapping("/send")
    public ResponseEntity<Void> createNewMessage(@RequestParam("content") String content,
                                                 @RequestParam("chatId") Long chatId,
                                                 @RequestParam(name = "files", required = false) List<MultipartFile> files) throws IOException {

        MessageDtoRequest messageDtoRequest = new MessageDtoRequest(0L, content, chatId);
        defaultMessageService.createNewMessage(messageDtoRequest, files);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @MessageMapping("/update")
    public ResponseEntity<Void> editMessage(@RequestBody MessageDtoRequest messageDtoRequest) throws IOException {

        defaultMessageService.editMessage(messageDtoRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByContent")
    public ResponseEntity<List<MessageSearchDto>> findByContent(@RequestBody MessageDtoRequest messageDtoRequest) {

        return ResponseEntity.ok().body(defaultMessageService.findByContent(messageDtoRequest.getContent()));
    }
}

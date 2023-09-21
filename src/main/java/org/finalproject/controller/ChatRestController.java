package org.finalproject.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.finalproject.dto.chat.ChatDto;
import org.finalproject.dto.chat.ChatDtoMapper;
import org.finalproject.dto.chat.ChatSpecDto;
import org.finalproject.entity.Chat;
import org.finalproject.service.DefaultChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatRestController {

    private final DefaultChatService defaultChatService;
    private final ChatDtoMapper chatDtoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ChatDto> getById(@PathVariable("id") Long id) {

        ChatDto chat = chatDtoMapper.decorateDto(defaultChatService.getById(id));
        if (chat == null) {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(chat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long chatId) {

        defaultChatService.deleteChatById(chatId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/participants/{userId}")
    public ResponseEntity<Void> addUser(@PathVariable("id") Long chatId, @PathVariable("userId") Long userId) {

        defaultChatService.addUserForChat(chatId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/participants")
    public ResponseEntity<List<ChatSpecDto>> getChatsForUserExceptUserId() {

        List<ChatSpecDto> chatSpecDtoList = defaultChatService.getChatsForUserExceptUserId();
        return ResponseEntity.ok().body(chatSpecDtoList);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ChatDto> createNewChat(@PathVariable("id") Long userId) {

        Chat chat = defaultChatService.createNewChat(userId);
        return ResponseEntity.ok().body(chatDtoMapper.decorateDto(chat));
    }

    @GetMapping("/unreadExist")
    public ResponseEntity<Boolean> findUnreadMessagesForUser() {

        return ResponseEntity.ok().body(defaultChatService.findUnreadMessagesForUser());
    }
}

package org.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.map.ObjectMapper;
import org.finalproject.dto.MessageDto;
import org.finalproject.dto.MessageDtoMapper;
import org.finalproject.dto.MessageDtoRequest;
import org.finalproject.entity.Message;
import org.finalproject.service.GeneralService;
import org.finalproject.service.RabbitMQProducerServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageRestController {

    private final GeneralService<Message> messageService;
    private final MessageDtoMapper messageDtoMapper;
    private final RabbitMQProducerServiceImpl rabbit;

    @GetMapping
    public List<MessageDto> getAll() {

        List<Message> messageList = messageService.findAll();
        return messageList.stream()
                .map(messageDtoMapper::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "updatedDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page messages = messageService.findAll(pageable);
        List<Message> messageList = messages.toList();
        List<MessageDto> messageDtoList = messageList.stream()
                .map(messageDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageDtoList);
    }

    @PostMapping
    public void createChat(@RequestBody MessageDtoRequest messageDtoRequest) {

        messageService.save(messageDtoMapper.convertToEntity(messageDtoRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        Message message = messageService.findEntityById(id);
        if (message == null) {
            return ResponseEntity.badRequest().body("Message not found");
        }
        return ResponseEntity.ok().body(messageDtoMapper.convertToDto(message));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMessage(@RequestBody MessageDtoRequest messageDtoRequest) {

        try {
            messageService.delete(messageDtoMapper.convertToEntity(messageDtoRequest));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long chatId) {

        try {
            messageService.deleteById(chatId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody MessageDtoRequest messageDtoRequest) throws IOException {
        
        try {
            Message messageEntity = messageDtoMapper.convertToEntity(messageDtoRequest);
            messageEntity.setCreatedDate(messageService.getOne(messageEntity.getId()).getCreatedDate());
            messageEntity.setCreatedBy(messageService.getOne(messageEntity.getId()).getCreatedBy());
            messageService.save(messageEntity);
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(messageDtoRequest);
            rabbit.sendMessage(message, "messageRoutingKey");
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

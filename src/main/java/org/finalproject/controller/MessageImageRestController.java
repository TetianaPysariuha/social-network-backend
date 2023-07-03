package org.finalproject.controller;


import lombok.RequiredArgsConstructor;
import org.finalproject.dto.MessageImageDto;
import org.finalproject.dto.MessageImageDtoMapper;
import org.finalproject.dto.MessageImageDtoRequest;
import org.finalproject.entity.MessageImage;
import org.finalproject.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messageImagines")
public class MessageImageRestController {

    private final GeneralService<MessageImage> messageImageService;
    private final MessageImageDtoMapper messageImageDtoMapper;

    @GetMapping
    public List<MessageImageDto> getAll() {

        List<MessageImage> messageImageList = messageImageService.findAll();
        return messageImageList.stream()
                .map(messageImageDtoMapper::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<?> findAll(@PathVariable Integer page, @PathVariable Integer size) {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "updatedDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page messageImagines = messageImageService.findAll(pageable);
        List<MessageImage> messageImageList = messageImagines.toList();
        List<MessageImageDto> messageImageDtoListtoList = messageImageList.stream()
                .map(messageImageDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageImageDtoListtoList);
    }

    @PostMapping
    public void createChat(@RequestBody MessageImageDtoRequest messageImageDtoRequest) {

        messageImageService.save(messageImageDtoMapper.convertToEntity(messageImageDtoRequest));
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@PathVariable("id") Long userId) {

        MessageImage messageImage = messageImageService.getOne(userId);
        if (messageImage == null) {
            return ResponseEntity.badRequest().body("MessageImage not found");
        }
        return ResponseEntity.ok().body(messageImageDtoMapper.convertToDto(messageImage));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMessageImage(@RequestBody MessageImageDtoRequest messageImageDtoRequest) {

        try {
            messageImageService.delete(messageImageDtoMapper.convertToEntity(messageImageDtoRequest));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long chatId) {

        try {
            messageImageService.deleteById(chatId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody MessageImageDtoRequest messageImageDtoRequest) {

        try {
            messageImageService.save(messageImageDtoMapper.convertToEntity(messageImageDtoRequest));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

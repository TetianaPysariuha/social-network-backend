package org.finalproject.service;

import org.finalproject.dto.chat.ChatSpecDto;
import org.finalproject.dto.chat.UserForChatDto;
import org.finalproject.entity.*;
import org.finalproject.repository.ChatRepository;
import org.finalproject.repository.MessageRepository;
import org.finalproject.service.jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
//@RequiredArgsConstructor
public class DefaultChatService extends GeneralService<Chat> {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GeneralService<User> generalUserService;
    @Autowired
    private GeneralService<Chat> chatService;
    @Autowired
    private GeneralService<User> userGeneralService;
    @Autowired
    private GeneralService<Message> messageGeneralService;
    @Autowired
    private DefaultMessageService defaultMessageService;

    public List<ChatSpecDto> getChatsForUserExceptUserId() {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.getByEmail(auth).get();
        List<ChatSpecProjection> projections = chatRepository.getChatsForUser(user.getId());
        List<ChatSpecDto> chatSpecDtoList = projections.stream()
                .map(ChatSpecDto::fromProjection).toList();
        for (ChatSpecDto chat : chatSpecDtoList) {
            List<UserForChatDto> userSpecDtos = findUsersFromChat(chat.getId());
            userSpecDtos.removeIf(userDto -> userDto.getId().equals(user.getId()));
            chat.setChatParticipant(userSpecDtos);
        }
        return chatSpecDtoList;
    }

    public List<Chat> findChatsByParticipant(Long userId, Long loggedUserId) {

        return chatRepository.findChatsByParticipant(userId, loggedUserId);
    }

    public List<UserForChatDto> findUsersFromChat(Long chatId) {

        List<UserSpecProjection> projections = chatRepository.findUsersFromChat(chatId);
        return projections.stream()
                .map(UserForChatDto::fromProjection)
                .collect(Collectors.toList());
    }

    public Chat createNewChat(Long userId) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User loggedUser = userService.getByEmail(auth).get();
        List<Chat> chats = findChatsByParticipant(userId, loggedUser.getId());
        Chat newChat = new Chat();
        for (Chat chat : chats) {
            List<User> users = chat.getUsers();
            if (users.size() == 2) {
                newChat = chat;
            }
        }
        if (newChat.getUsers() == null) {
            User user = generalUserService.getOne(userId);
            List<User> userList = List.of(user, loggedUser);
            List<Message> messageList = new ArrayList<>();
            List<MessageImage> messageImageList = new ArrayList<>();
            Chat chat = new Chat(messageList, messageImageList, userList);
            newChat = chatService.save(chat);
            loggedUser.getChats().add(newChat);
            generalUserService.save(loggedUser);
            user.getChats().add(newChat);
            generalUserService.save(user);
        }
        return newChat;
    }

    public void deleteChatById(Long chatId) {

        Chat chat = chatService.findEntityById(chatId);
        List<User> users = chat.getUsers();
        for (User user : users) {
            user.getChats().removeIf(userChat -> userChat.getId().equals(chatId));
            user.getReadMessages().removeIf(m -> m.getChatId().equals(chatId));
        }
        System.out.println("Users from CHAT: " + users);
        chatService.deleteById(chatId);
    }

    public void addUserForChat(Long chatId, Long userId) {

        Chat chat = chatService.findEntityById(chatId);
        User user = userGeneralService.findEntityById(userId);
        List<User> userList = chat.getUsers();
        userList.add(user);
        chat.setUsers(userList);
        Chat chatFromDb = chatService.save(chat);
        user.getChats().add(chatFromDb);
        userGeneralService.save(user);
    }

    public Chat getById(Long chatId) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User loggedUser = userService.getByEmail(auth).get();
        List<Message> unReadMessages = loggedUser.getReadMessages();
        unReadMessages.removeIf(msg -> msg.getChat().getId().equals(chatId));
        userGeneralService.save(loggedUser);
        Chat chat = chatService.findEntityById(chatId);
        return chat;
    }

    public boolean findUnreadMessagesForUser() {

        String auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User loggedUser = userService.getByEmail(auth).get();
        return chatRepository.findUnreadMessagesForUser(loggedUser.getId()) != 0;
    }

}

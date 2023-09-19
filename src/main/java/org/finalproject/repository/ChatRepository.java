package org.finalproject.repository;

import org.finalproject.entity.Chat;
import org.finalproject.entity.ChatSpecProjection;
import org.finalproject.entity.UserSpecProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends RepositoryInterface<Chat>, JpaSpecificationExecutor<Chat> {

    @Query(value = "SELECT " +
            "c.id AS id, " +
            "u.id AS userId, " +
            "u.full_name AS fullName, " +
            "u.profile_picture AS profilePicture, " +
            "m.content AS content, " +
            "m.id AS messageId, " +
            "m.created_date AS lastMessageDate, " +
            "(SELECT count(*) FROM messages msg " +
            "JOIN message_status ms ON msg.id = ms.message_id " +
            "WHERE msg.chat_id = c.id AND ms.user_id = :id) AS messageCount " +
            "FROM " +
            "chats c " +
            "LEFT JOIN " +
            "messages m " +
            "ON c.id = m.chat_id " +
            "LEFT JOIN " +
            "users u " +
            "ON m.user_id = u.id " +
            "LEFT JOIN " +
            "users_chats uc " +
            "ON c.id = uc.chat_id " +
            "WHERE " +
            "uc.user_id = :id " +
            "AND (m.id IS NULL OR m.id = (SELECT MAX(m2.id) " +
            "FROM messages m2 " +
            "WHERE m2.chat_id = c.id)) " +
            "ORDER BY " +
            "lastMessageDate DESC;", nativeQuery = true)
    List<ChatSpecProjection> getChatsForUser(@Param("id") Long userId);


    @Query(value = "SELECT DISTINCT c.* " +
            "FROM chats c " +
            "JOIN users_chats uc ON c.id = uc.chat_id " +
            "JOIN users u ON uc.user_id = u.id " +
            "WHERE u.id = :userId " +
            "AND c.id IN (SELECT cc.chat_id FROM users_chats cc WHERE cc.user_id = :loggedUserId)",
            nativeQuery = true)
    List<Chat> findChatsByParticipant(@Param("userId") Long userId, @Param("loggedUserId") Long loggedUserId);

    @Query(value = "SELECT " +
            "u.id AS id, " +
            "u.full_name AS fullName, " +
            "u.profile_picture AS profilePicture " +
            "FROM users u " +
            "JOIN users_chats uc ON u.id = uc.user_id " +
            "WHERE uc.chat_id = :id", nativeQuery = true)
    List<UserSpecProjection> findUsersFromChat(@Param("id") Long chatId);

    @Query(value = "SELECT count(*) FROM messages msg " +
            "  JOIN message_status ms ON msg.id = ms.message_id" +
            "  WHERE ms.user_id = :userId", nativeQuery = true)
    Long findUnreadMessagesForUser(@Param("userId") Long userId);

}

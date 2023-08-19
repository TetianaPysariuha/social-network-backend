package org.finalproject.repository;

import org.finalproject.entity.Chat;
import org.finalproject.entity.ChatSpecProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends RepositoryInterface<Chat>, JpaSpecificationExecutor<Chat> {

    @Query(value = "SELECT DISTINCT " +
            "c.id AS id, " +
            "uc.user_id AS userId, " +
            "u.full_name AS fullName, " +
            "u.profile_picture AS profilePicture, " +
            "m.content AS content, " +
            "m.created_date AS lastMessageDate " +
            "FROM " +
            "chats c " +
            "JOIN " +
            "users_chats uc ON c.id = uc.chat_id " +
            "JOIN " +
            "users u ON uc.user_id = u.id " +
            "LEFT JOIN " +
            "messages m ON c.id = m.chat_id " +
            "WHERE " +
            "u.id <> :id " +
            "AND c.id IN (SELECT cc.chat_id FROM users_chats cc WHERE cc.user_id = :id) " +
            "AND m.id = ( " +
            "SELECT MAX(m2.id) " +
            "FROM messages m2 " +
            "WHERE m2.chat_id = c.id " +
            ")", nativeQuery = true)
    List<ChatSpecProjection> getChatsForUserExceptUserId(@Param("id") Long userId);

    @Query(value = "SELECT DISTINCT " +
            "c.id AS id, " +
            "uc.user_id AS userId, " +
            "u.full_name AS fullName, " +
            "u.profile_picture AS profilePicture, " +
            "m.content AS content, " +
            "m.created_date AS lastMessageDate " +
            "FROM " +
            "chats c " +
            "JOIN " +
            "users_chats uc ON c.id = uc.chat_id " +
            "JOIN " +
            "users u ON uc.user_id = u.id " +
            "LEFT JOIN " +
            "messages m ON c.id = m.chat_id " +
            "WHERE " +
            "u.id = :userId " +
            "AND c.id IN (SELECT cc.chat_id FROM users_chats cc WHERE cc.user_id = :loggedUserId) " +
            "AND m.id = ( " +
            "SELECT MAX(m2.id) " +
            "FROM messages m2 " +
            "WHERE m2.chat_id = c.id " +
            ")", nativeQuery = true)
    List<ChatSpecProjection> findChatsByParticipant(@Param("userId") Long userId, @Param("loggedUserId") Long loggedUserId);

}

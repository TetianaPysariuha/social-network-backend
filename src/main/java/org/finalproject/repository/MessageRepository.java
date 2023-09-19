package org.finalproject.repository;

import org.finalproject.entity.Message;
import org.finalproject.entity.MessageSearchProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends RepositoryInterface<Message>, JpaSpecificationExecutor<Message> {

    @Query(value = "SELECT m.id, m.content, m.created_date AS createdDate, m.updated_date AS updatedDate, " +
            "m.created_by AS createdBy, m.updated_by AS updatedBy, m.chat_id AS chatId," +
            " u.id AS userId, u.full_name AS fullName, " +
            "u.profile_picture AS profilePicture " +
            "FROM messages m " +
            "INNER JOIN users_chats uc1 ON m.user_id = uc1.user_id " +
            "INNER JOIN users_chats uc2 ON uc1.chat_id = uc2.chat_id " +
            "INNER JOIN users u ON m.user_id = u.id " +
            "WHERE LOWER(m.content) LIKE LOWER(concat('%', :content, '%')) " +
            "AND uc2.user_id = :userId", nativeQuery = true)
    List<MessageSearchProjection> findByContent(@Param("content") String content, @Param("userId") Long userId);

    @Query(value = "SELECT message_id FROM message_status " +
            "WHERE user_id = :userId AND message_id = :messageId", nativeQuery = true)
    List<Long> findUnReadMessages(@Param("userId") Long userId, @Param("messageId") Long messageId);

    @Query(value = "DELETE FROM message_status WHERE user_id = :userId  AND message_id = :messageId", nativeQuery = true)
    void deleteFromMessageStatus(@Param("userId") Long userId, @Param("messageId") Long messageId);
}

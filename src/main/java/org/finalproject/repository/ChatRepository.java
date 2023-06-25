package org.finalproject.repository;

import org.finalproject.entity.Chat;
import org.finalproject.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends RepositoryInterface {

    @Query("Select m FROM Message m WHERE m.content LIKE %:content%")
    List<Message> findByContent (@Param("content") String content);


}

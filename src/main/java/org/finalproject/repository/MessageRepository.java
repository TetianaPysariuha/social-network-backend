package org.finalproject.repository;

import org.finalproject.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends RepositoryInterface<Message>, JpaSpecificationExecutor<Message>{

//    @Query("Select m FROM Message m WHERE m.content LIKE %:content%")
//    List<Message> findByContent (@Param("content") String content);


}

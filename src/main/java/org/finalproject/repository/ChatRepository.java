package org.finalproject.repository;

import org.finalproject.entity.Chat;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends RepositoryInterface<Chat>, JpaSpecificationExecutor<Chat> {

//    need to be in UserRepo?
//    @Query("Select id FROM Users m WHERE fullName LIKE %:partOfName% ")
//    List<Long> findUserIdByPartOfName(String partOfName);


}

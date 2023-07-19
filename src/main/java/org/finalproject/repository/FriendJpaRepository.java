package org.finalproject.repository;

import org.finalproject.entity.Friend;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendJpaRepository extends RepositoryInterface<Friend>, JpaSpecificationExecutor<Friend> {

    @Query(value = "select f from Friend f where f.friend.id in (:id) or f.user.id in (:id) ")
    List<Friend> findFriends(@Param("id")  Long id );


}

package org.finalproject.repository;

import org.finalproject.entities.Friend;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendJpaRepository extends RepositoryInterface<Friend>, JpaSpecificationExecutor<Friend> {



}

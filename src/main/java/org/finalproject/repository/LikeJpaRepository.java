package org.finalproject.repository;

import org.finalproject.entity.Friend;
import org.finalproject.entity.Like;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LikeJpaRepository extends RepositoryInterface<Like>, JpaSpecificationExecutor<Like> {

}

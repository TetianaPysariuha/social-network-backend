package org.finalproject.repository;


import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.finalproject.entity.UserImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface UserImageRepository extends RepositoryInterface<UserImage>, JpaSpecificationExecutor<UserImage> {

    Page<UserImage> findAllByUser(User user, Pageable pageable );

}

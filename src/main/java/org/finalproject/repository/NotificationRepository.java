package org.finalproject.repository;

import org.finalproject.entity.Notification;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends RepositoryInterface<Notification>, JpaSpecificationExecutor<Notification> {

    Page<Notification> findAllByReceiverContains(User user, Pageable pageable);

}
package org.finalproject.repository;

import org.finalproject.entity.Notification;
import org.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationRepository extends RepositoryInterface<Notification>, JpaSpecificationExecutor<Notification> {


}
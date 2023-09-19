package org.finalproject.repository;

import org.finalproject.entity.Notification;
import org.finalproject.entity.Post;
import org.finalproject.entity.User;
import org.finalproject.util.NotificationStatus;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NotificationRepository extends RepositoryInterface<Notification>, JpaSpecificationExecutor<Notification> {

    Page<Notification> findAllByReceiverContains( User user, Pageable pageable );

    Page<Notification> findAllByReceiverAndStatus(User user, NotificationStatus status, Pageable pageable );

    @Query(value = "SELECT n.id as id,n.sender_id as sender,n.content as content,n.type as type,n.status"
            + " as status,n.updated_entity_id as updatedEntityId ,n.created_date as createdDate,"
            + " n.updated_date AS updatedDate,n.created_by AS createdBy, n.updated_by AS updatedBy"
            + " from notifications n  LEFT  JOIN users_notifications un  ON n.id = un.notification_id"
            + " WHERE un.user_id =:userId  ", nativeQuery = true)
    Page<Notification> findAuthUserNotifications(@Param("userId")  Long userId, Pageable pageable );
}
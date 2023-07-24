package org.finalproject.repository;

import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FriendJpaRepository extends RepositoryInterface<Friend>, JpaSpecificationExecutor<Friend> {

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id)")
    List<Friend> friendsOfUser(Long userId);

    @Query("select u from User u where u.id not in (" +
            "select f.user.id from Friend f where :userId = f.friend.id" +
            " union" +
            " select f.friend.id from Friend f where :userId = f.user.id)" +
            " and u.id != :userId")
    List<User> suggestedUsersForFriendship(Long userId);

    @Query("select u from User u where u.id in (" +
            "select t.friendID from (" +
            "select f.user.id friendID from Friend f where :userId = f.friend.id and f.status = 'accepted' " +
            "union all " +
            "select f.friend.id friendID from Friend f where :userId = f.user.id and f.status = 'accepted' " +
            "union all " +
            "select f.user.id friendID from Friend f where :friendId = f.friend.id and f.status = 'accepted' " +
            "union all " +
            "select f.friend.id friendID from Friend f where :friendId = f.user.id and f.status = 'accepted') t " +
            "group by t.friendID having count(*) > 1) " +
            "and u.id not in (:userId, :friendId)")
    List<User> getMutualFriends(Long userId, Long friendId);

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id) and :friendId in (f.friend.id, f.user.id)")
    List<Friend> getFriendByBothID(Long userId, Long friendId);
}

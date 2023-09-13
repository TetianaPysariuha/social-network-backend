package org.finalproject.repository;

import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendJpaRepository extends RepositoryInterface<Friend>, JpaSpecificationExecutor<Friend> {

    @Query(value = "select f from Friend f where f.friend.id in (:id) or f.user.id in (:id) ")
    List<Friend> findFriends(@Param("id")  Long id );

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id)")
    List<Friend> friendsOfUser(Long userId);

    @Query("select u from User u where u.id not in (" +
            "select f.user.id from Friend f where :userId = f.friend.id" +
            " union" +
            " select f.friend.id from Friend f where :userId = f.user.id)" +
            " and u.id != :userId")
    List<User> suggestedUsersForFriendship(Long userId);

    @Query(value ="with userfriends as (select CASE WHEN friend_id = :id THEN user_id" +
           " END friendID,status " +
                  "  from friends ff" +
            " where 1 in (ff.user_id, ff.friend_id))" +
            " select * from users u" +
            " left join userfriends s on u.id = s.friendID" +
            " where u.id in (" +
            " select fr.friend_id notfriend" +
            " from friends fr" +
            "join userfriends s on fr.user_id = s.friendID" +
            " where s.status in ('accepted')" +
            "and 1 not in (fr.user_id, fr.friend_id)" +
            " and fr.status = 'accepted'" +
            "union" +
            " select fr.user_id notfriend" +
            "from friends fr" +
            " join userfriends s on fr.friend_id = s.friendID" +
            " where s.status in ('accepted')" +
            " and 1 not in (fr.user_id, fr.friend_id)" +
            "and fr.status = 'accepted')" +
            " and s.friendID is null" +
            " LIMIT 100)", nativeQuery = true)
    List<User> FriendsForFriendship(Long userId);

    @Query("select u from User u where u.id in (" +
                    "select t.friendID from (" +
                    "select CASE WHEN f.friend.id in (:userId, :friendId) THEN f.user.id " +
                    "WHEN f.user.id in (:userId, :friendId) THEN f.friend.id " +
                    "END friendID " +
                    "from Friend f " +
                    "where f.status = 'accepted' " +
                    "and (f.user.id in (:userId, :friendId) or f.friend.id in (:userId, :friendId))) t " +
                    "group by t.friendID having count(*) > 1) " +
                    "and u.id not in (:userId, :friendId)")
    List<User> getMutualFriends(Long userId, Long friendId);

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id) and :friendId in (f.friend.id, f.user.id)")
    List<Friend> getFriendByBothID(Long userId, Long friendId);
}

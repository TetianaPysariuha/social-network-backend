package org.finalproject.repository;

import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FriendJpaRepository extends RepositoryInterface<Friend>, JpaSpecificationExecutor<Friend> {

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id) and f.status = 'accepted'")
    List<Friend> friendsOfUser(Long userId);

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id) and f.status = 'accepted'")
    List<Friend> friendsOfUser(Long userId, Pageable pageable);

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id) and f.status = 'pending'")
    List<Friend> friendRequests(Long userId);

    @Query("select f from Friend f where :userId in (f.friend.id, f.user.id) and f.status = 'pending'")
    List<Friend> friendRequests(Long userId, Pageable pageable);

    @Query("select u from User u where u.id not in (" +
            "select f.user.id from Friend f where :userId = f.friend.id and f.status in ('pending', 'accepted', 'removed')" +
            " union" +
            " select f.friend.id from Friend f where :userId = f.user.id and f.status in ('pending', 'accepted', 'removed'))" +
            " and u.id != :userId")
    List<User> suggestedUsersForFriendship(Long userId);

    @Query("select u from User u where u.id not in (" +
            "select f.user.id from Friend f where :userId = f.friend.id and f.status in ('pending', 'accepted', 'removed')" +
            " union" +
            " select f.friend.id from Friend f where :userId = f.user.id and f.status in ('pending', 'accepted', 'removed'))" +
            " and u.id != :userId")
    List<User> suggestedUsersForFriendship(Long userId, Pageable pageable);

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

    @Query("select f from Friend f " +
            "where f.status = 'accepted' " +
            "and :userId in (f.friend.id, f.user.id) " +
            "and ((upper(f.friend.fullName) like upper(concat('%', :namePart, '%')) and f.friend.id <> :userId) " +
            "or (upper(f.user.fullName) like upper(concat('%', :namePart, '%')) and f.user.id <> :userId))")
    List<Friend> getFriendByUserIdFriendName(Long userId, String namePart, Pageable pageable);
}

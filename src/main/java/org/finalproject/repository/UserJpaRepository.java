package org.finalproject.repository;



import lombok.NonNull;
import org.finalproject.entity.Friend;
import org.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserJpaRepository extends RepositoryInterface<User>, JpaSpecificationExecutor<User> {

    public Optional<User> getByEmail(@NonNull String email) ;

    public Optional<User> getByFullName(@NonNull String fullName) ;

    @Query(value = " from User  where fullName like %:part%")
    List<User> findUserByPartOfName(@Param("part")  String part );

}

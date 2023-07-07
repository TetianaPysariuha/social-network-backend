package org.finalproject.repository;



import lombok.NonNull;
import org.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface UserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    public Optional<User> getByEmail(@NonNull String email) ;


}

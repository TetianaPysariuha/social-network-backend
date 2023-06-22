package org.finalproject.repository;



import lombok.NonNull;
import org.finalproject.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface UserJpaRepository extends RepositoryInterface<User>, JpaSpecificationExecutor<User> {

    public Optional<User> getByEmail(@NonNull String email) ;

}

package org.finalproject.repository;



import org.finalproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserJpaRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {



}

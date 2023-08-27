package org.finalproject.repository;

import org.finalproject.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostJpaRepository extends RepositoryInterface<Post> {
    @Query("SELECT p FROM Post p WHERE p.postType = 'post'")
    List<Post> findAllPosts();

    @Query("SELECT p FROM Post p WHERE p.postType = 'post'")
    Page<Post> findAllPosts(Pageable pageable);

}

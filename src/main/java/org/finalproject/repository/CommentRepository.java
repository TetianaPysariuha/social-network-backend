package org.finalproject.repository;


import org.finalproject.entity.ImgComment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface CommentRepository extends RepositoryInterface<ImgComment>, JpaSpecificationExecutor<ImgComment> {



}

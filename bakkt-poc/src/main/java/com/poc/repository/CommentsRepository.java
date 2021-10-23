package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poc.entity.Comments;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
	

}

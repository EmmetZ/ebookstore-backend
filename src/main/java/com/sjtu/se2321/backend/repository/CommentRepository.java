package com.sjtu.se2321.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Page<Comment> findAllByBookId(Long bookId, Pageable pageable);

}
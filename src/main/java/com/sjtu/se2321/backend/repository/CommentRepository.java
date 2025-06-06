package com.sjtu.se2321.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sjtu.se2321.backend.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query(value = "UPDATE Comment SET `like` = `like` + :like_count WHERE id = :id", nativeQuery = true)
    public void updateLike(@Param("id") Long id, @Param("like_count") int like);

    public Page<Comment> findAllByBookId(Long bookId, Pageable pageable);

}
package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.CommentLike;
import com.sjtu.se2321.backend.entity.CommentLikeId;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {

}

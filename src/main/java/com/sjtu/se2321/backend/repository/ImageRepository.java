package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

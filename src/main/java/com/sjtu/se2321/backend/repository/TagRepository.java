package com.sjtu.se2321.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjtu.se2321.backend.entity.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {

    public Tag findByName(String name);

}
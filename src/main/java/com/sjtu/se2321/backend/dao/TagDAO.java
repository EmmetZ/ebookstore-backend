package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Tag;

public interface TagDAO {

    public List<Tag> findAll();

    public Tag findByName(String name);

}

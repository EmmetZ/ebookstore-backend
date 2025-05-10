package com.sjtu.se2321.backend.dao;

import java.util.List;

import com.sjtu.se2321.backend.entity.Tag;

public interface TagDAO {

    public List<Tag> getAllTags();

    public Tag getTagById(Long id);

    public List<Tag> getTagByBookId(Long bookId);

    public Tag getTagByName(String name);

}

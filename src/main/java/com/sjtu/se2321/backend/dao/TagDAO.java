package com.sjtu.se2321.backend.dao;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.entity.Tag;

public interface TagDAO {

    public List<Tag> getAllTags();

    public Optional<Tag> getTagById(Long id);

    public Optional<List<Tag>> getTagByBookId(Long bookId);

    public Optional<Tag> getTagByName(String name);

}

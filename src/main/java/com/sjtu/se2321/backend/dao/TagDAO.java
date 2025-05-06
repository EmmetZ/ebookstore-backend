package com.sjtu.se2321.backend.dao;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.entity.Tag;

public interface TagDAO {

    public List<Tag> getAllTags();

    public Optional<Tag> getTagById(Integer id);

    public Optional<List<Tag>> getTagByBookId(Integer bookId);

}

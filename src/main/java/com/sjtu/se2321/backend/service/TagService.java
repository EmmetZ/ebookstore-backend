package com.sjtu.se2321.backend.service;

import java.util.List;
import java.util.Optional;

import com.sjtu.se2321.backend.entity.Tag;

public interface TagService {

    public List<String> getAllTags();

    public Optional<Tag> getTagById(Integer id);

}

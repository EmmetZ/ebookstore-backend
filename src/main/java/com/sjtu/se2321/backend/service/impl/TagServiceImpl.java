package com.sjtu.se2321.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.entity.Tag;
import com.sjtu.se2321.backend.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Override
    public List<String> getAllTags() {
        return tagDAO.getAllTags().stream()
                .map(tag -> tag.getName())
                .toList();
    }

    @Override
    public Optional<Tag> getTagById(Integer id) {
        return tagDAO.getTagById(id);
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        return tagDAO.getTagByName(name);
    }
}
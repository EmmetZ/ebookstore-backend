package com.sjtu.se2321.backend.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.TagDAO;
import com.sjtu.se2321.backend.entity.Tag;
import com.sjtu.se2321.backend.repository.TagRepository;

@Component
public class TagDAOImpl implements TagDAO {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> getTagByBookId(Long bookId) {
        return tagRepository.findByBookId(bookId);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
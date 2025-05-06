package com.sjtu.se2321.backend.dao.impl;

import java.util.List;
import java.util.Optional;

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
    public Optional<Tag> getTagById(Integer id) {
        return tagRepository.findById(id);
    }

    @Override
    public Optional<List<Tag>> getTagByBookId(Integer bookId) {
        return tagRepository.findByBookId(bookId);
    }
}
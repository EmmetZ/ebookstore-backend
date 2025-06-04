package com.sjtu.se2321.backend.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjtu.se2321.backend.dao.ImageDAO;
import com.sjtu.se2321.backend.entity.Image;
import com.sjtu.se2321.backend.repository.ImageRepository;

@Component
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getReferenceById(Long id) {
        return imageRepository.getReferenceById(id);
    }

}

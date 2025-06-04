package com.sjtu.se2321.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.ImageDAO;
import com.sjtu.se2321.backend.entity.Image;
import com.sjtu.se2321.backend.service.ImageService;

import jakarta.transaction.Transactional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    @Transactional
    public Image save(Image image) {
        return imageDAO.save(image);
    }

    @Override
    public Image getReferenceById(Long id) {
        return imageDAO.getReferenceById(id);
    }

}

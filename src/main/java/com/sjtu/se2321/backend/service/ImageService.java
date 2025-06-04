package com.sjtu.se2321.backend.service;

import com.sjtu.se2321.backend.entity.Image;

public interface ImageService {

    public Image save(Image image);

    public Image getReferenceById(Long id);

}

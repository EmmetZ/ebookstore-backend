package com.sjtu.se2321.backend.dao;

import com.sjtu.se2321.backend.entity.Image;

public interface ImageDAO {

    public Image save(Image image);

    public Image getReferenceById(Long id);

}

package com.sjtu.se2321.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "cover")
public class BookCover extends Image {

    public BookCover(String fileName) {
        this.setFileName(fileName);
    }

    public static String getDefaultFileName() {
        return "default_cover.jpg";
    }
}

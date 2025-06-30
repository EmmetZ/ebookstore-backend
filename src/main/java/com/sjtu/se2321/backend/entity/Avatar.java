package com.sjtu.se2321.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "avatar")
public class Avatar extends Image {

    @OneToOne(mappedBy = "avatar")
    private User user;

    public Avatar(String fileName) {
        this.setFileName(fileName);
    }

    public static Avatar defaultAvatar() {
        return new Avatar("default_user.jpg"); 
    }

    public static String getDefaultFileName() {
        return "default_user.jpg";
    }

}

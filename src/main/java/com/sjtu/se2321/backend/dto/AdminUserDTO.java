package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.User;

import lombok.Data;

@Data
public class AdminUserDTO {
    private Long id;
    private String username;
    private String nickname;
    private String introduction;
    private String avatar;
    private User.Role role;
    private Boolean isBanned;

    public AdminUserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.introduction = user.getIntroduction();
        this.avatar = user.getAvatar() != null ? user.getAvatar().getFileName() : null;
        this.role = user.getRole();
        this.isBanned = user.getIsBanned();
    }
}

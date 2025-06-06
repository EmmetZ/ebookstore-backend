package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OtherUserDTO {
    private String username;
    private String nickname;
    private String introduction;
    private String avatar;

    public OtherUserDTO(User user) {
        this.avatar = user.getAvatar().getFileName();
        this.introduction = user.getIntroduction();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}

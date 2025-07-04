package com.sjtu.se2321.backend.dto;

import com.sjtu.se2321.backend.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String nickname;
    private Integer balance;
    private String introduction;
    private String avatar;
    private User.Role role;

    public static UserDTO fromUser(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getNickname(),
                user.getBalance(),
                user.getIntroduction(),
                user.getAvatar().getFileName(),
                user.getRole());
    }
}

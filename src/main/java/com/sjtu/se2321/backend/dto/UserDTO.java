package com.sjtu.se2321.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sjtu.se2321.backend.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String nickname;
    private String balance;
    private String introduction;
    private String avatar;

    @JsonProperty("isAdmin")
    private boolean isAdmin;

    public static UserDTO fromUser(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getNickname(),
                user.getBalance(),
                user.getIntroduction(),
                user.getAvatar(),
                user.getIsAdmin() == 1 ? true : false);
    }
}

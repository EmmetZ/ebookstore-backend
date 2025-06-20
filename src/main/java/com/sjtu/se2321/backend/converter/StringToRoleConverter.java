package com.sjtu.se2321.backend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.User.Role;

public class StringToRoleConverter implements Converter<String, User.Role> {

    @Override
    public Role convert(@NonNull String source) {
        try {
            return User.Role.fromValue(source);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

}

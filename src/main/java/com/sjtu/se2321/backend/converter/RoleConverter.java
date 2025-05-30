package com.sjtu.se2321.backend.converter;

import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.User.Role;

import jakarta.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<User.Role, String> {

    @Override
    public String convertToDatabaseColumn(User.Role role) {
        return role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(String value) {
        return User.Role.fromValue(value);
    }

}

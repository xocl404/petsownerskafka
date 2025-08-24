package org.xocl404.mapper;

import org.springframework.stereotype.Component;
import org.xocl404.UserDto;
import org.xocl404.entity.User;


@Component
public class UserMapper {
    public UserDto toDto (User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .ownerId(user.getOwnerId())
                .build();
    }
}

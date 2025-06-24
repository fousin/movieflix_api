package dev.fousin.movieflix.mapper;

import dev.fousin.movieflix.controller.request.UserRequest;
import dev.fousin.movieflix.controller.response.UserResponse;
import dev.fousin.movieflix.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}

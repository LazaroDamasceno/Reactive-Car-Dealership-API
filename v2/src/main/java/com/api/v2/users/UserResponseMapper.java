package com.api.v2.users;

import com.api.v2.users.domain.User;
import com.api.v2.users.dtos.UserResponseDto;

public class UserResponseMapper {

    public static UserResponseDto map(User user) {
        return new UserResponseDto(
                user.getFullName(),
                user.getBirthDate(),
                user.getSsn(),
                user.getEmail(),
                user.getGender(),
                user.getPhoneNumber()
        );
    }

}

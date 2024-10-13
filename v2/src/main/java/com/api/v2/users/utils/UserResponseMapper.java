package com.api.v2.users.utils;

import com.api.v2.users.domain.User;
import com.api.v2.users.dtos.UserResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserResponseMapper {

    public UserResponseDto map(User user) {
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

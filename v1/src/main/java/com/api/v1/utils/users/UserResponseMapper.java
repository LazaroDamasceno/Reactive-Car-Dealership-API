package com.api.v1.utils.users;

import com.api.v1.domain.users.Users;
import com.api.v1.dtos.users.UserResponseDto;

public class UserResponseMapper {

    public static UserResponseDto map(Users users) {
        return new UserResponseDto(
                users.getFullName(),
                users.getSsn(),
                users.getBirthDate(),
                users.getEmail(),
                users.getGender(),
                users.getPhoneNumber()
        );
    }

}

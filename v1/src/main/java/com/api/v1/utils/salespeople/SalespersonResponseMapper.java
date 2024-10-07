package com.api.v1.utils.salespeople;

import com.api.v1.domain.salespeople.Salespeople;
import com.api.v1.dtos.salespeople.SalespersonResponseDto;
import com.api.v1.utils.users.UserResponseMapper;

public class SalespersonResponseMapper {

    public static SalespersonResponseDto map(Salespeople salesperson) {
        return new SalespersonResponseDto(
                salesperson.getEmployeeId(),
                UserResponseMapper.map(salesperson.getUser())
        );
    }

}

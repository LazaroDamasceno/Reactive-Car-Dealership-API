package com.api.v1.utils.customers;

import com.api.v1.domain.customers.Customers;
import com.api.v1.dtos.customers.CustomerResponseDto;
import com.api.v1.utils.users.UserResponseMapper;

public class CustomerResponseMapper {

    public static CustomerResponseDto map(Customers customers) {
        return new CustomerResponseDto(
                customers.getAddress(),
                UserResponseMapper.map(customers.getUser())
        );
    }

}

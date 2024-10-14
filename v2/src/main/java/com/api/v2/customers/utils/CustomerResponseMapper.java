package com.api.v2.customers.utils;

import com.api.v2.customers.domain.Customer;
import com.api.v2.customers.dtos.CustomerResponseDto;
import com.api.v2.users.utils.UserResponseMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerResponseMapper {

    public CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                customer.getAddress(),
                UserResponseMapper.map(customer.getUser())
        );
    }

}

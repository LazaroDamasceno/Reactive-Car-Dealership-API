package com.api.v2.customers;

import com.api.v2.users.utils.UserResponseMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerRequestMapper {

    public CustomerResponseDto map(Customer customer) {
        return new CustomerResponseDto(
                customer.getAddress(),
                UserResponseMapper.map(customer.getUser())
        );
    }

}

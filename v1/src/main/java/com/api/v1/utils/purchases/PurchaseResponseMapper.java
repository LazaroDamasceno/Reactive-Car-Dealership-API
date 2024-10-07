package com.api.v1.utils.purchases;

import com.api.v1.domain.purchases.Purchases;
import com.api.v1.dtos.purchases.PurchaseResponseDto;
import com.api.v1.utils.cars.CarResponseMapper;
import com.api.v1.utils.customers.CustomerResponseMapper;
import com.api.v1.utils.salespeople.SalespersonResponseMapper;

import java.time.ZonedDateTime;

public class PurchaseResponseMapper {

    public static PurchaseResponseDto map(Purchases purchase) {
        return new PurchaseResponseDto(
                purchase.idNumber(),
                CarResponseMapper.map(purchase.car()),
                CustomerResponseMapper.map(purchase.customers()),
                SalespersonResponseMapper.map(purchase.salesperson()),
                purchase.finalPrice(),
                ZonedDateTime.ofInstant(purchase.createdAt(), purchase.createdAtZone())
        );
    }

}

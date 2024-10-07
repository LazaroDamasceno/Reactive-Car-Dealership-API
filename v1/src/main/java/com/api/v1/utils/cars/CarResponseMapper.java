package com.api.v1.utils.cars;

import com.api.v1.domain.cars.Cars;
import com.api.v1.dtos.cars.CarResponseDto;

public class CarResponseMapper {

    public static CarResponseDto map(Cars car) {
        return new CarResponseDto(
                car.getMake(),
                car.getModel(),
                car.getVin(),
                car.getProductionYear(),
                car.getPrice()
        );
    }

}

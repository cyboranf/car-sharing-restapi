package com.example.carental.seed;

import com.example.carental.model.CarType;
import com.example.carental.model.enums.CarTypeValue;
import com.example.carental.repository.CarTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
@Profile("seed")
public class CarTypeSeeder implements DatabaseSeeder {
    private final CarTypeRepository carTypeRepository;

    public CarTypeSeeder(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    /**
     * CarType:
     * "1": "SEDAN",
     * "2": "SUV",
     * "3": "COUPE",
     * "4": "Truck"
     * "5": "VAN"
     */
    @Override
    public void run(String... args) throws Exception {
        if (carTypeRepository.count() == 0) {
            for (CarTypeValue value : CarTypeValue.values()) {
                CarType carType = new CarType();
                carType.setType(value);
                carTypeRepository.save(carType);
            }
        }
    }
}

package com.example.carental.seed;

import com.example.carental.model.CarStatus;
import com.example.carental.model.enums.CarStatusValue;
import com.example.carental.repository.CarStatusRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@Profile("seed")
public class CarStatusSeeder implements DatabaseSeeder {
    private final CarStatusRepository carStatusRepository;

    public CarStatusSeeder(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    /**
     * CarStatus:
     * "1": "AVAILABLE",
     * "2": "RENTED",
     * "3": "UNDER_MAINTENANCE"
     */
    @Override
    public void run(String... args) throws Exception {
        if (carStatusRepository.count() == 0) {
            for (CarStatusValue value : CarStatusValue.values()) {
                CarStatus carStatus = new CarStatus();
                carStatus.setStatus(value);
                carStatusRepository.save(carStatus);
            }
        }
    }
}

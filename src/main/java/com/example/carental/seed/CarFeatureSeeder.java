package com.example.carental.seed;

import com.example.carental.model.CarFeature;
import com.example.carental.repository.CarFeatureRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(7)
@Profile("seed")
public class CarFeatureSeeder implements DatabaseSeeder {
    private final CarFeatureRepository carFeatureRepository;

    public CarFeatureSeeder(CarFeatureRepository carFeatureRepository) {
        this.carFeatureRepository = carFeatureRepository;
    }

    /**
     * CarFeature:
     * "1": "Air Conditioning",
     * "2": "Bluetooth",
     * "3": "Cruise Control",
     * "4": "Parking Sensors"
     */
    @Override
    public void run(String... args) throws Exception {
        if (carFeatureRepository.count() == 0) {
            List<CarFeature> carFeatures = Arrays.asList(
                    newCarFeature("Air Conditioning"),
                    newCarFeature("Bluetooth"),
                    newCarFeature("Cruise Control"),
                    newCarFeature("Parking Sensors")
            );

            carFeatureRepository.saveAll(carFeatures);
        }
    }

    protected CarFeature newCarFeature(String name) {
        CarFeature carFeature = new CarFeature();
        carFeature.setName(name);
        return carFeature;
    }
}

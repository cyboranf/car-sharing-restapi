package com.example.carental.seed;

import com.example.carental.model.Manufacturer;
import com.example.carental.repository.ManufacturerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(4)
@Profile("seed")
public class ManufacturerSeeder implements DatabaseSeeder {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerSeeder(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * Manufacturer:
     * "1": "Toyota",
     * "2": "Ford",
     * "3": "Hyundai",
     * "4": "Chevrolet"
     */
    @Override
    public void run(String... args) throws Exception {
        if (manufacturerRepository.count() == 0) {
            List<Manufacturer> manufacturers = Arrays.asList(
                    newManufacturer("Toyota"),
                    newManufacturer("Ford"),
                    newManufacturer("Hyundai"),
                    newManufacturer("Chevrolet")
            );

            manufacturerRepository.saveAll(manufacturers);
        }
    }

    private Manufacturer newManufacturer(String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        return manufacturer;
    }
}

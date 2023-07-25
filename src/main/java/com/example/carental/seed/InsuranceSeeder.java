package com.example.carental.seed;

import com.example.carental.model.Car;
import com.example.carental.model.Insurance;
import com.example.carental.model.enums.InsuranceProvider;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.InsuranceRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Order(10)
@Profile("seed")
public class InsuranceSeeder implements DatabaseSeeder {
    private final InsuranceRepository insuranceRepository;
    private final CarRepository carRepository;

    public InsuranceSeeder(InsuranceRepository insuranceRepository, CarRepository carRepository) {
        this.insuranceRepository = insuranceRepository;
        this.carRepository = carRepository;
    }

    /**
     * Insurances:
     * "1": "Policy1",
     * "2": "Policy2",
     * "3": "Policy3",
     * "4": "Policy4"
     */
    @Override
    public void run(String... args) throws Exception {
        if (insuranceRepository.count() == 0) {
            List<Insurance> insurances = Arrays.asList(
                    newInsurance("Policy1", InsuranceProvider.ALLIANZ, LocalDate.now(), LocalDate.now().plusYears(1), 1L),
                    newInsurance("Policy2", InsuranceProvider.AXA, LocalDate.now(), LocalDate.now().plusYears(1), 2L),
                    newInsurance("Policy3", InsuranceProvider.ZURICH, LocalDate.now(), LocalDate.now().plusYears(1), 3L),
                    newInsurance("Policy4", InsuranceProvider.AVIVA, LocalDate.now(), LocalDate.now().plusYears(1), 4L)
            );

            insuranceRepository.saveAll(insurances);
        }
    }

    private Insurance newInsurance(String policyNumber, InsuranceProvider provider, LocalDate startDate, LocalDate endDate, Long carId) {
        Insurance insurance = new Insurance();
        insurance.setPolicyNumber(policyNumber);
        insurance.setProvider(provider);
        insurance.setStartDate(startDate);
        insurance.setEndDate(endDate);

        Car car = carRepository.findById(carId).orElse(null);
        insurance.setCar(car);

        return insurance;
    }
}

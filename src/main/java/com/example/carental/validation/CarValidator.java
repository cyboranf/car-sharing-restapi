package com.example.carental.validation;


import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.exception.car.CarNotFoundException;
import com.example.carental.exception.car.InvalidCarBrandOrModelException;
import com.example.carental.exception.car.InvalidCarDescriptionException;
import com.example.carental.exception.car.InvalidCountOfSeatsException;
import com.example.carental.exception.carFeature.CarFeatureNotFoundException;
import com.example.carental.exception.carStatus.CarStatusNotFoundException;
import com.example.carental.exception.carStatus.InvalidCarStatusException;
import com.example.carental.exception.carType.CarTypeNotFoundException;
import com.example.carental.exception.carType.InvalidCarTypeException;
import com.example.carental.exception.manufacturer.ManufacturerNotFoundException;
import com.example.carental.exception.user.UserNotFoundException;
import com.example.carental.model.Car;
import com.example.carental.model.CarStatus;
import com.example.carental.model.CarType;
import com.example.carental.model.enums.CarStatusValue;
import com.example.carental.model.enums.CarTypeValue;
import com.example.carental.repository.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CarValidator {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarTypeRepository carTypeRepository;
    private final CarStatusRepository carStatusRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CarFeatureRepository carFeatureRepository;

    public CarValidator(CarRepository carRepository, UserRepository userRepository, CarTypeRepository carTypeRepository, CarStatusRepository carStatusRepository, ManufacturerRepository manufacturerRepository, CarFeatureRepository carFeatureRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.carTypeRepository = carTypeRepository;
        this.carStatusRepository = carStatusRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.carFeatureRepository = carFeatureRepository;
    }

    private static final Map<String, List<String>> carBrandsAndModels = new HashMap<>();

    static {
        carBrandsAndModels.put("Audi", Arrays.asList("A3", "A4", "Q5", "R8", "TT", "Q7"));
        carBrandsAndModels.put("BMW", Arrays.asList("3 Series", "X3", "X5", "M4", "7 Series", "i3", "i8"));
        carBrandsAndModels.put("Tesla", Arrays.asList("Model 3", "Model S", "Model X", "Model Y"));
        carBrandsAndModels.put("Mercedes-Benz", Arrays.asList("C-Class", "E-Class", "S-Class", "GLA", "GLC", "G-Class"));
        carBrandsAndModels.put("Toyota", Arrays.asList("Camry", "Corolla", "Highlander", "Land Cruiser", "Prius", "RAV4"));
        carBrandsAndModels.put("Ford", Arrays.asList("Mustang", "Fiesta", "Focus", "Explorer", "F-150"));
        carBrandsAndModels.put("Chevrolet", Arrays.asList("Camaro", "Corvette", "Cruze", "Impala", "Silverado", "Suburban"));
        carBrandsAndModels.put("Honda", Arrays.asList("Civic", "Accord", "CR-V", "Pilot", "Ridgeline"));
        carBrandsAndModels.put("Hyundai", Arrays.asList("Elantra", "Sonata", "Tucson", "Santa Fe"));
        carBrandsAndModels.put("Nissan", Arrays.asList("Altima", "Maxima", "Rogue", "Titan", "370Z"));
        carBrandsAndModels.put("Volkswagen", Arrays.asList("Golf", "Passat", "Tiguan", "Jetta", "Beetle"));
        carBrandsAndModels.put("Porsche", Arrays.asList("911", "Cayman", "Boxster", "Cayenne", "Panamera", "Macan"));
        carBrandsAndModels.put("Volvo", Arrays.asList("S60", "V90", "XC60", "XC90"));
        carBrandsAndModels.put("Jeep", Arrays.asList("Wrangler", "Cherokee", "Grand Cherokee", "Compass", "Renegade"));
        carBrandsAndModels.put("Subaru", Arrays.asList("Outback", "Forester", "Impreza", "Legacy", "BRZ"));
        carBrandsAndModels.put("Lexus", Arrays.asList("ES", "IS", "LS", "RX", "GX"));
        carBrandsAndModels.put("Ferrari", Arrays.asList("488GTB", "812 Superfast", "Portofino", "F8 Tributo", "SF90 Stradale"));
        carBrandsAndModels.put("Lamborghini", Arrays.asList("Aventador", "Huracan", "Urus"));
        carBrandsAndModels.put("McLaren", Arrays.asList("570S", "720S", "Senna", "Speedtail", "Elva"));
        carBrandsAndModels.put("Bugatti", Arrays.asList("Chiron", "Divo", "Veyron", "Centodieci"));
    }

    /**
     * @param carRequestDTO
     */
    public void addCarValidation(CarRequestDTO carRequestDTO) {
        if (!carBrandsAndModels.containsKey(carRequestDTO.getBrand()) || !carBrandsAndModels.get(carRequestDTO.getBrand()).contains(carRequestDTO.getModel())) {
            throw new InvalidCarBrandOrModelException("The car brand or model provided is not recognized. Please verify the details and try again.");
        }
        if (carRequestDTO.getDescription() == null || carRequestDTO.getDescription().length() > 1000) {
            throw new InvalidCarDescriptionException("The description of car can not have more than 1000 characters length.");
        }
        if (carRequestDTO.getSeats() > 7 || carRequestDTO.getSeats() < 0) {
            throw new InvalidCountOfSeatsException("Invalid number of seats. The number of seats in a car should be between 0 and 7 inclusive. Please verify the details and try again.");
        }
        userRepository.findById(carRequestDTO.getOwnerId())
                .orElseThrow(() -> new UserNotFoundException("Can not found owner with id = " + carRequestDTO.getOwnerId()));
        CarStatus carStatus = carStatusRepository.findById(carRequestDTO.getStatusId())
                .orElseThrow(() -> new CarStatusNotFoundException("Wrong status of car."));
        if (Arrays.stream(CarStatusValue.values()).noneMatch(val -> val.name().equals(carStatus.getStatus().name()))) {
            throw new InvalidCarStatusException("Invalid car status. The status should be one of the following: AVAILABLE, RENTED, UNDER_MAINTENANCE. Please verify the details and try again.");
        }
        CarType carType = carTypeRepository.findById(carRequestDTO.getTypeId())
                .orElseThrow(() -> new CarTypeNotFoundException("Wrong type of car."));
        if (Arrays.stream(CarTypeValue.values()).noneMatch(val -> val.name().equals(carType.getType().name()))) {
            throw new InvalidCarTypeException("The type of the car fetched from the database is invalid. The type should be one of the following: SEDAN, SUV, COUPE, TRUCK, VAN. Please verify the details and try again.");
        }
        manufacturerRepository.findById(carRequestDTO.getManufacturerId())
                .orElseThrow(() -> new ManufacturerNotFoundException("Can not find manufacturer with id = " + carRequestDTO.getManufacturerId()));
        carRequestDTO.getFeatureIds().stream()
                .forEach(featureId -> carFeatureRepository.findById(featureId)
                        .orElseThrow(() -> new CarFeatureNotFoundException("A feature with ID " + featureId + " could not be found. Please verify the details and try again.")));
    }

    /**
     * @param carId
     * @param carRequestDTO
     */
    public void updateCarValidation(Long carId, CarRequestDTO carRequestDTO) {
        if (!carRepository.existsById(carId)) {
            throw new CarNotFoundException("The car with id = " + carId + " does not exist.");
        }
        addCarValidation(carRequestDTO);
    }

    /**
     * @param carId
     * @return
     */
    public Car getByIdValidation(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car not found with ID: " + carId));
    }
}

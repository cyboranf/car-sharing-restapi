package com.example.carental.validation;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.exception.car.CarNotFoundException;
import com.example.carental.exception.rent.InvalidRentDateException;
import com.example.carental.exception.rent.RentNotFoundException;
import com.example.carental.model.Rent;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.RentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RentValidator {
    private final RentRepository rentRepository;
    private final CarRepository carRepository;


    public RentValidator(RentRepository rentRepository, CarRepository carRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
    }

    /**
     * @param rentRequestDTO
     */
    public void rentCarValidation(RentRequestDTO rentRequestDTO) {
        rentRepository.findById(rentRequestDTO.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Can not rent car with id = " + rentRequestDTO.getCarId()
                        + ", because Car with this id doesn't exists. "));
        if (rentRequestDTO.getStartDate() == null || rentRequestDTO.getEndDate() == null
                || rentRequestDTO.getStartDate().isBefore(LocalDateTime.now())
                || rentRequestDTO.getStartDate().isBefore(rentRequestDTO.getEndDate())) {
            throw new InvalidRentDateException("Wrong date of rent. Check it one more time, please.");
        }
    }

    /**
     * @param id
     * @return
     */
    public Rent getRentByIdValidation(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException("Can not found rent with id = " + id));
    }
}

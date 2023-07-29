package com.example.carental.service;

import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.mapper.CarMapper;
import com.example.carental.model.Car;
import com.example.carental.model.enums.CarStatusValue;
import com.example.carental.repository.CarRepository;
import com.example.carental.validation.CarValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarValidator carValidator;

    @Mock
    private CarMapper carMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCar() {
        CarRequestDTO request = new CarRequestDTO();
        request.setBrand("Brand1");
        request.setModel("Model1");
        request.setDescription("Description1");
        request.setSeats(5);
        request.setOwnerId(1L);
        request.setStatusId(1L);
        request.setTypeId(1L);
        request.setManufacturerId(1L);
        request.setFeatureIds(new HashSet<>(Arrays.asList(1L, 2L)));

        Car car = new Car();
        car.setBrand("Brand1");
        car.setModel("Model1");
        car.setDescription("Description1");
        car.setSeats(5);

        CarResponseDTO responseDTO = new CarResponseDTO();
        responseDTO.setBrand("Brand1");
        responseDTO.setModel("Model1");
        responseDTO.setDescription("Description1");
        responseDTO.setSeats(5);

        when(carMapper.fromDTO(any(CarRequestDTO.class))).thenReturn(car);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        when(carMapper.toDTO(any(Car.class))).thenReturn(responseDTO);

        CarResponseDTO response = carService.addCar(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetAllCars() {
        Car car1 = new Car();
        car1.setBrand("Brand1");
        car1.setModel("Model1");

        Car car2 = new Car();
        car2.setBrand("Brand2");
        car2.setModel("Model2");

        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));
        when(carMapper.toDTO(car1)).thenReturn(new CarResponseDTO());
        when(carMapper.toDTO(car2)).thenReturn(new CarResponseDTO());

        List<CarResponseDTO> response = carService.getAllCars();

        assertEquals(2, response.size());
    }

    @Test
    public void testGetAllAvailableCars() {
        Car car1 = new Car();
        car1.setBrand("Brand1");
        car1.setModel("Model1");

        Car car2 = new Car();
        car2.setBrand("Brand2");
        car2.setModel("Model2");

        when(carRepository.findByStatus_Status(CarStatusValue.AVAILABLE)).thenReturn(Arrays.asList(car1, car2));
        when(carMapper.toDTO(car1)).thenReturn(new CarResponseDTO());
        when(carMapper.toDTO(car2)).thenReturn(new CarResponseDTO());

        List<CarResponseDTO> response = carService.getAllAvailableCars();

        assertEquals(2, response.size());
    }

    @Test
    public void testUpdateCar() {
        Long carId = 1L;

        CarRequestDTO request = new CarRequestDTO();
        request.setBrand("Brand1");
        request.setModel("Model1");

        Car car = new Car();
        car.setId(carId);
        car.setBrand("Brand1");
        car.setModel("Model1");

        CarResponseDTO responseDTO = new CarResponseDTO();
        responseDTO.setBrand("Brand1");
        responseDTO.setModel("Model1");

        when(carMapper.fromDTO(request)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDTO(car)).thenReturn(responseDTO);

        CarResponseDTO response = carService.updateCar(carId, request);

        assertEquals(responseDTO, response);
    }


    @Test
    public void testFindCarById() {
        Long carId = 1L;

        Car car = new Car();
        car.setId(carId);
        car.setBrand("Brand1");
        car.setModel("Model1");

        CarResponseDTO responseDTO = new CarResponseDTO();
        responseDTO.setBrand("Brand1");
        responseDTO.setModel("Model1");

        when(carValidator.getByIdValidation(anyLong())).thenReturn(car);
        when(carMapper.toDTO(any(Car.class))).thenReturn(responseDTO);

        CarResponseDTO response = carService.findCarById(carId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteCar() {
        Long carId = 1L;

        Car car = new Car();
        car.setId(carId);
        car.setBrand("Brand1");
        car.setModel("Model1");

        CarResponseDTO responseDTO = new CarResponseDTO();
        responseDTO.setBrand("Brand1");
        responseDTO.setModel("Model1");

        when(carValidator.getByIdValidation(anyLong())).thenReturn(car);
        when(carMapper.toDTO(any(Car.class))).thenReturn(responseDTO);

        CarResponseDTO response = carService.deleteCar(carId);

        assertEquals(responseDTO, response);
    }
}

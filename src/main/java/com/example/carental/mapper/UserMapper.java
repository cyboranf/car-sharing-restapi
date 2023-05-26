package com.example.carental.mapper;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.model.Car;
import com.example.carental.model.CarFeature;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public  UserResponseDTO mapUserToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setContactsCount(user.getContactsCount());
        dto.setMsgCount(user.getMsgCount());
        dto.setActive(user.isActive());

        if (user.getAddress() != null) {
            dto.setAddressId(user.getAddress().getId());
        }

        dto.setRoleIds(user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()));  // map roles to roleIds

        return dto;
    }
    public CarResponseDTO mapCarToCarResponseDTO(Car car) {
        CarResponseDTO dto = new CarResponseDTO();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setDescription(car.getDescription());
        dto.setSeats(car.getSeats());

        // Assuming that Car class has getOwner() and the returned User object has getId() method
        if (car.getOwner() != null) {
            dto.setOwnerId(car.getOwner().getId());
        }

        if (car.getStatus() != null) {
            dto.setStatusId(car.getStatus().getId());
        }
        if (car.getType() != null) {
            dto.setTypeId(car.getType().getId());
        }
        if (car.getManufacturer() != null) {
            dto.setManufacturerId(car.getManufacturer().getId());
        }

        if (car.getFeatures() != null) {
            dto.setFeatureIds(car.getFeatures().stream()
                    .map(CarFeature::getId)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

}

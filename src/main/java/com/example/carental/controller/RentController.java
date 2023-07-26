package com.example.carental.controller;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping("/bookings/{bookingId}/rents")
    public ResponseEntity<RentResponseDTO> createRent(@RequestBody RentRequestDTO rentRequestDTO) {
        RentResponseDTO responseDTO = rentService.rentCar(rentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/users/{userId}/rents")
    public ResponseEntity<List<RentResponseDTO>> getRentsByUserId(@PathVariable Long userId) {
        List<RentResponseDTO> responseDTOs = rentService.getRentsByUserId(userId);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/rents/{rentId}")
    public ResponseEntity<RentResponseDTO> getRent(@PathVariable Long rentId) {
        RentResponseDTO responseDTO = rentService.getRentById(rentId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/rents/{rentId}")
    public ResponseEntity<RentResponseDTO> updateRent(@PathVariable Long rentId, @RequestBody RentRequestDTO rentRequestDTO) {
        RentResponseDTO responseDTO = rentService.updateRent(rentId, rentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/rents/{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) {
        rentService.deleteRentById(rentId);
        return ResponseEntity.noContent().build();
    }
}

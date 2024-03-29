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

    /**
     * @param rentRequestDTO
     * @return DTO of new rent
     */
    @PostMapping("/bookings/rents")
    public ResponseEntity<RentResponseDTO> createRent(@RequestBody RentRequestDTO rentRequestDTO) {
        RentResponseDTO responseDTO = rentService.rentCar(rentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * @param rentId
     * @param rentRequestDTO
     * @return update Rent with id = @param
     */
    @PutMapping("/rents/{rentId}")
    public ResponseEntity<RentResponseDTO> updateRent(@PathVariable Long rentId, @RequestBody RentRequestDTO rentRequestDTO) {
        RentResponseDTO responseDTO = rentService.updateRent(rentId, rentRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * @param rentId
     * @return DTO of deleted Rent
     */
    @DeleteMapping("/rents/{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) {
        rentService.deleteRentById(rentId);
        return ResponseEntity.noContent().build();
    }
}

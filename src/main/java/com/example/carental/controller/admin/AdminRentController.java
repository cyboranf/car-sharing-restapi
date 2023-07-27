package com.example.carental.controller.admin;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRentController {
    private final RentService rentService;

    public AdminRentController(RentService rentService) {
        this.rentService = rentService;
    }

    /**
     * @param userId
     * @return DTO of all rents by user with id = @param
     */
    @GetMapping("/users/{userId}/rents")
    public ResponseEntity<List<RentResponseDTO>> getRentsByUserId(@PathVariable Long userId) {
        List<RentResponseDTO> responseDTOs = rentService.getRentsByUserId(userId);
        return ResponseEntity.ok(responseDTOs);
    }

    /**
     * @param rentId
     * @return DTO of rent
     */
    @GetMapping("/rents/{rentId}")
    public ResponseEntity<RentResponseDTO> getRent(@PathVariable Long rentId) {
        RentResponseDTO responseDTO = rentService.getRentById(rentId);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * @param rentId
     * @param rentRequestDTO
     * @return DTO of updated Rent
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

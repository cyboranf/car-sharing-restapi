package com.example.carental.controller.admin;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return DTO of all users in car-sharing system
     */
    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * @param userId
     * @return DTO of deleted User with id = @param
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    /**
     * @param userId
     * @return DTO of user with id = @param
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    /**
     * @param userId
     * @param userRequestDTO
     * @return updated User with id = @param userId
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequestDTO));
    }

    /**
     * @param userId
     * @return DTO's of cars rented by user with id = @param
     */
    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<CarResponseDTO>> getUsersCars(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUsersCars(userId));
    }
}

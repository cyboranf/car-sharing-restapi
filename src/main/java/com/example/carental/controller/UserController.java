package com.example.carental.controller;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
     * @return DTO of edited profile
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequestDTO));
    }

    /**
     * @param userId
     * @return DTO's of all user with id = @param Cars
     */
    @GetMapping("/{userId}/cars")
    public ResponseEntity<List<CarResponseDTO>> getUsersCars(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUsersCars(userId));
    }
}

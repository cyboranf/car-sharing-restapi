package com.example.carental.controller.admin;

import com.example.carental.dto.image.ImageResponseDTO;
import com.example.carental.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminImageController {
    private final ImageService imageService;

    public AdminImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * @return All images of Car in car-sharing system
     */
    @GetMapping("/images")
    public ResponseEntity<List<ImageResponseDTO>> getAllImages() {
        List<ImageResponseDTO> images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted image with id = @param
     */
    @DeleteMapping("/image/{id}")
    public ResponseEntity<ImageResponseDTO> getImageById(@PathVariable Long id) {
        ImageResponseDTO deletedImage = imageService.deleteImage(id);
        return new ResponseEntity<>(deletedImage, HttpStatus.OK);
    }
}

package com.example.carental.validation;

import com.example.carental.dto.image.ImageRequestDTO;
import com.example.carental.exception.car.CarNotFoundException;
import com.example.carental.exception.image.ImageNotFoundException;
import com.example.carental.exception.image.InvalidImageURLException;
import com.example.carental.model.Image;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.ImageRepository;
import org.springframework.stereotype.Component;

@Component
public class ImageValidator {
    private final ImageRepository imageRepository;
    private final CarRepository carRepository;

    public ImageValidator(ImageRepository imageRepository, CarRepository carRepository) {
        this.imageRepository = imageRepository;
        this.carRepository = carRepository;
    }

    /**
     * @param imageRequestDTO
     */
    public void validateImageRequest(ImageRequestDTO imageRequestDTO) {
        if (imageRequestDTO.getUrl() == null || imageRequestDTO.getUrl().isEmpty()) {
            throw new InvalidImageURLException("Image URL cannot be empty");
        }
        if (imageRequestDTO.getCarId() == null || !carRepository.existsById(imageRequestDTO.getCarId())) {
            throw new CarNotFoundException("Invalid car ID");
        }
    }

    /**
     * @param imageId
     * @return Image with id = imageId
     */
    public Image getByIdImageValidation(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException("Can not found image with id = " + imageId));
    }

}

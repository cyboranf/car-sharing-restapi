package com.example.carental.service;

import com.example.carental.dto.image.ImageRequestDTO;
import com.example.carental.dto.image.ImageResponseDTO;
import com.example.carental.mapper.ImageMapper;
import com.example.carental.model.Image;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.ImageRepository;
import com.example.carental.validation.ImageValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;
    private final CarRepository carRepository;
    private final ImageMapper imageMapper;
    private final ImageValidator imageValidator;

    public ImageService(ImageRepository imageRepository, CarRepository carRepository, ImageMapper imageMapper, ImageValidator imageValidator) {
        this.imageRepository = imageRepository;
        this.carRepository = carRepository;
        this.imageMapper = imageMapper;
        this.imageValidator = imageValidator;
    }

    /**
     * @param imageRequestDTO
     * @return DTO of new image
     */
    public ImageResponseDTO uploadImage(ImageRequestDTO imageRequestDTO) {
        imageValidator.validateImageRequest(imageRequestDTO);

        Image image = imageMapper.fromDTO(imageRequestDTO);

        image.setCar(carRepository.getById(imageRequestDTO.getCarId()));

        Image savedImage = imageRepository.save(image);

        return imageMapper.toDTO(savedImage);
    }

    /**
     * @return DTO of all images
     */
    public List<ImageResponseDTO> getAllImages() {
        return imageRepository.findAll().stream()
                .map(imageMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param imageId
     * @return DTO of image with id = imageId
     */
    public ImageResponseDTO getImageById(Long imageId) {
        Image image = imageValidator.getByIdImageValidation(imageId);
        return imageMapper.toDTO(image);
    }

    /**
     * @param id
     * @param imageRequestDTO
     * @return DTO of updated Image
     */
    public ImageResponseDTO updateImage(Long id, ImageRequestDTO imageRequestDTO) {
        imageValidator.validateImageRequest(imageRequestDTO);
        Image image = imageValidator.getByIdImageValidation(id);

        image.setUrl(imageRequestDTO.getUrl());

        image.setCar(carRepository.getById(imageRequestDTO.getCarId()));

        Image savedImage = imageRepository.save(image);

        return imageMapper.toDTO(savedImage);
    }

    /**
     * @param id
     * @return DTO of deleted Image
     */
    public ImageResponseDTO deleteImage(Long id) {
        Image image = imageValidator.getByIdImageValidation(id);
        imageRepository.delete(image);
        return imageMapper.toDTO(image);
    }
}

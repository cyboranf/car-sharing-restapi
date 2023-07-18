package com.example.carental.mapper;

import com.example.carental.model.Image;
import com.example.carental.dto.image.ImageRequestDTO;
import com.example.carental.dto.image.ImageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageResponseDTO toDTO(Image image) {
        ImageResponseDTO dto = new ImageResponseDTO();
        dto.setId(image.getId());
        dto.setUrl(image.getUrl());
        dto.setCarId(image.getCar().getId());
        return dto;
    }

    public Image fromDTO(ImageRequestDTO imageRequestDTO) {
        Image image = new Image();
        image.setUrl(imageRequestDTO.getUrl());
        return image;
    }
}

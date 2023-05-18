package com.example.carental.dto.image;

import lombok.Data;

@Data
public class ImageResponseDTO {
    private Long id;
    private String url;
    private Long carId;
}

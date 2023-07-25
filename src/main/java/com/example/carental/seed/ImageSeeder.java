package com.example.carental.seed;

import com.example.carental.model.Car;
import com.example.carental.model.Image;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.ImageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(9)
@Profile("seed")
public class ImageSeeder implements DatabaseSeeder {
    private final ImageRepository imageRepository;
    private final CarRepository carRepository;

    public ImageSeeder(ImageRepository imageRepository, CarRepository carRepository) {
        this.imageRepository = imageRepository;
        this.carRepository = carRepository;
    }

    /**
     * Images:
     * "1": "https://example.com/image1.jpg",
     * "2": "https://example.com/image2.jpg",
     * "3": "https://example.com/image3.jpg",
     * "4": "https://example.com/image4.jpg"
     */
    @Override
    public void run(String... args) throws Exception {
        if (imageRepository.count() == 0) {
            List<Image> images = Arrays.asList(
                    newImage("https://example.com/image1.jpg", 1L),
                    newImage("https://example.com/image2.jpg", 2L),
                    newImage("https://example.com/image3.jpg", 3L),
                    newImage("https://example.com/image4.jpg", 4L)
            );

            imageRepository.saveAll(images);
        }
    }

    private Image newImage(String url, Long carId) {
        Image image = new Image();
        image.setUrl(url);

        Car car = carRepository.findById(carId).orElse(null); // Assuming car with ID exists
        image.setCar(car);

        return image;
    }
}

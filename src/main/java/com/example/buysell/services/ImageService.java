package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image findById(Product product){
        return imageRepository.findById(product.getPreviewImageId()).orElse(null);
    }
    public Image findById(Long id){
        return imageRepository.findById(id).orElse(null);
    }

    public Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteImages(List<Image> images){
        images.forEach(image -> {
            imageRepository.myDeleteById(image.getId());
        });
    }
}

package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ImageService imageService;

    private final AuthService authService;
    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws  IOException {
        product.setUser(authService.getUserByPrincipal(principal));
        Image  image1;
        Image  image2;
        Image  image3;
        if(file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if(file2.getSize() != 0){
            image2 = toImageEntity(file2);
            image2.setPreviewImage(true);
            product.addImageToProduct(image2);
        }
        if(file3.getSize() != 0){
            image3 = toImageEntity(file3);
            image3.setPreviewImage(true);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    private Image toImageEntity(MultipartFile file) throws IOException{
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public List<Image> getPreviewOfProducts(List<Product> products){
        List<Image> images = new ArrayList<>();
        for(Product product: products){
            images.add(imageService.findById(product));
        }
        return images;
    }

    public List<Image> getListOfPreviewOfProduct(User user){
        List<Product> products = user.getProducts();
        return getPreviewOfProducts(products);
    }

    public List<Product> findByUserLike(User user){
        return productRepository.findByUserLike(user.getId());
    }

    public boolean isLikeExists(User user, Long productId){
        return productRepository.isLikeExists(user.getId(), productId);
    }

    public void saveUserLike(User user, Long productId){
        productRepository.saveUserLike(user.getId(), productId);
    }

    public Product findById(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    public void deleteUserLike(User user,Long productId){
        productRepository.deleteUserLike(user.getId(),productId);
    }
}
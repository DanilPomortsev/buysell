package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.repositories.ImageRepository;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(email).isPresent()) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return (List<User>) userRepository.findAll();
    }

    public List<Product> getListOfLikedProduct(User user){
        return productService.findByUserLike(user);
    }

    public void saveLike(User user, Long productId){
        if(!productService.isLikeExists(user, productId)){
            productService.saveUserLike(user, productId);
            user.getLikes().add(productService.findById(productId));
        }
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

    public void addAvatar(MultipartFile file, User user) throws IOException {
        Image  image;
        if(file.getSize() != 0){
            image = toImageEntity(file);
            if(user.getAvatar() != null){
                imageRepository.deleteById(user.getAvatar().getId());
            }
            user.setAvatar(image);
        }
        log.info("Saving new Avatar of user {}", user.getEmail());
        User userFromDb = userRepository.save(user);
    }

    public boolean isLiked(User user, Long productId){
        return productService.isLikeExists(user, productId);
    }

    public void deleteIfExistsLike(User user, Long productId){
        if(productService.isLikeExists(user,productId)){
            productService.deleteUserLike(user,productId);
        }
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
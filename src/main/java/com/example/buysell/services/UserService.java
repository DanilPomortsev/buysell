package com.example.buysell.services;

import com.example.buysell.models.Image;
import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.repositories.ImageRepository;
import com.example.buysell.repositories.ProductRepository;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.getRoles().add(Role.ROLE_USER);
        //user.getRoles().add(Role.ROLE_ADMIN);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles =
                Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
    public List<User> list() {
        return userRepository.findAll();
    }

    public List<Image> getPreviwOfProducts(List<Product> products){
        List<Image> images = new ArrayList<>();
        for(Product product: products){
            images.add(imageRepository.findById(product.getPreviewImageId()).orElse(null));
        }
        return images;
    }

    public List<Image> getListOfPreviewOfProduct(User user){
        List<Product> products = user.getProducts();
        return getPreviwOfProducts(products);
    }


    public List<Product> getListOfLikedProduct(User user){
        return productRepository.findByUserLike(user.getId());
    }

    public void saveLike(User user, Long productId){
        if(!productRepository.isLikeExists(user.getId(), productId)){
            productRepository.saveUserLike(user.getId(), productId);
            user.getLikes().add(productRepository.findById(productId).orElse(null));
        }
    }
}

package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.services.ProductService;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final ProductService productService;

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }


    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("userByPrincipal", productService.getUserByPrincipal(principal));
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        model.addAttribute("images", userService.getListOfPreviewOfProduct(user));
        return "user-info";
    }

    @GetMapping("/like")
    public String userLike(Model model, Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        List<Product> likedProducts = userService.getListOfLikedProduct(user);
        model.addAttribute("products", likedProducts);
        model.addAttribute("images", userService.getPreviwOfProducts(likedProducts));
        return "user-like";

    }
}
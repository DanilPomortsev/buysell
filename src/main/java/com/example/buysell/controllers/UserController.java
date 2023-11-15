package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.services.AuthService;
import com.example.buysell.services.ProductService;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final AuthService authService;

    private final ProductService productService;

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "login";
    }

    @PostMapping("/login")
    public String succesLogin(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "products";
    }


    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal){
        User user = authService.getUserByPrincipal(principal);
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "profile";
    }

    @PostMapping("/avatar")
    public String avatar(@RequestParam("file") MultipartFile file, Model model, Principal principal) throws IOException {
        User user = authService.getUserByPrincipal(principal);
        userService.addAvatar(file, user);
        model.addAttribute("user", user);
        return "redirect:/profile";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("userByPrincipal", authService.getUserByPrincipal(principal));
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        model.addAttribute("images", productService.getListOfPreviewOfProduct(user));
        return "user-info";
    }

    @GetMapping("/like")
    public String userLike(Model model, Principal principal) {
        User user = authService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        List<Product> likedProducts = userService.getListOfLikedProduct(user);
        model.addAttribute("products", likedProducts);
        model.addAttribute("images", productService.getPreviewOfProducts(likedProducts));
        return "user-like";
    }
}
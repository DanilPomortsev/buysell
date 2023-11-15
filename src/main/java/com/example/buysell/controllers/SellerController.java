package com.example.buysell.controllers;

import com.example.buysell.endpoints.SellerDataEndpoint;
import com.example.buysell.models.User;
import com.example.buysell.services.AuthService;
import com.example.buysell.services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class SellerController {
    private final AuthService authService;
    private final SellerService sellerService;

    @PostMapping("/seller-data")
    public String sendSellerData(SellerDataEndpoint sellerData, Principal principal, Model model){
        User user = authService.getUserByPrincipal(principal);
        sellerService.becomeSeller(user, sellerData);
        model.addAttribute("user",user);
        return "redirect:/profile";
    }

    @GetMapping("/seller-data")
    public String sellerDataPage(Principal principal, Model model){
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "seller-data";
    }

    @GetMapping("/my/products")
    public String myProducts(Model model, Principal principal){
        User user = authService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "my-products";
    }
}

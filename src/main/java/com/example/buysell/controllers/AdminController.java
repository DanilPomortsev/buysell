package com.example.buysell.controllers;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.services.AdminService;
import com.example.buysell.services.AuthService;
import com.example.buysell.services.ProductService;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AuthService authService;
    private final AdminService adminService;
    private final ProductService productService;

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "admin";
    }

    @GetMapping("/admin/moderation")
    public String moderation(Model model, Principal principal) {
        model.addAttribute("products", productService.listUnpmoderateProducts());
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "moderation-products";
    }

    @GetMapping("/admin/moderation/product/{id}")
    public String moderation(@PathVariable Long id, Model model, Principal principal) {
        User user = authService.getUserByPrincipal(principal);
        Product product = productService.getProductById(id);
        model.addAttribute("user", user);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "moderate-product-info";
    }

    @PostMapping("/admin/moderation/product/{id}")
    public String moderation(@PathVariable Long id, Model model, Principal principal,
                             @RequestParam(name = "moderateResult") Boolean moderateResult,
                             @RequestParam(name = "deactivateReason", required = false) String deactivateReason) {
        Product product = productService.getProductById(id);
        if(moderateResult){
            productService.successfulModerate(product);
        }
        else {
            adminService.unsuccessfulModerate(product,deactivateReason);
        }
        return "redirect:/admin/moderation";
    }
}

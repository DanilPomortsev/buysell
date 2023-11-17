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

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final AuthService authService;

    private final UserService userService;

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal){
        User user = authService.getUserByPrincipal(principal);
        Product product = productService.getProductById(id);
        model.addAttribute("isLiked", userService.isLiked(user, product.getId()));
        model.addAttribute("user", user);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }

    @PostMapping("/product/{id}")
    public String likeProduct(@RequestParam(name = "like") Boolean like,
                              @PathVariable Long id, Model model, Principal principal){
        User user = authService.getUserByPrincipal(principal);
        if(like){
            userService.saveLike(user, id);
        }
        else{
              userService.deleteIfExistsLike(user, id);
        }
        return "redirect:/product/{id}";
    }

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listActiveProducts(title));
        model.addAttribute("user", authService.getUserByPrincipal(principal));
        return "products";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2
                                , @RequestParam("file3") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/my/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @PostMapping("/product/change-name/{id}")
    public String productChangeName(@PathVariable long id, @RequestParam(name = "newName") String newName){
        Product product = productService.findById(id);
        productService.changeName(product, newName);
        return "redirect:/my/product/{id}";
    }
    @PostMapping("/product/change-price/{id}")
    public String productChangePrice(@PathVariable long id, @RequestParam(name = "newPrice") int newPrice){
        Product product = productService.findById(id);
        productService.changePrice(product, newPrice);
        return "redirect:/my/product/{id}";
    }

    @PostMapping("/product/change-city/{id}")
    public String productChangeCity(@PathVariable long id, @RequestParam(name = "newCity") String newCity){
        Product product = productService.findById(id);
        productService.changeCity(product, newCity);
        return "redirect:/my/product/{id}";
    }

    @PostMapping("/product/change-description/{id}")
    public String productChangeDescription(@PathVariable long id, @RequestParam(name = "newDescription") String newDescription){
        Product product = productService.findById(id);
        productService.changeDescription(product, newDescription);
        return "redirect:/my/product/{id}";
    }

    @PostMapping("/product/change-photos/{id}")
    public String productChangePhotos(@PathVariable long id, @RequestParam("file1") MultipartFile file1,
                                      @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3) throws IOException {
        Product product = productService.findById(id);
        productService.changePhotos(product, file1, file2, file3);
        return "redirect:/my/product/{id}";
    }
}

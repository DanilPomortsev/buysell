package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.models.ProductAdminInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final ProductAdminInfoService productAdminInfoService;
    private final ProductService productService;

    public void deactivateProduct(ProductAdminInfo productAdminInfo, String adminMessage){
        productAdminInfo.setModerate(true);
        productAdminInfo.setDeactivateReason(adminMessage);
        productAdminInfo.setDateDeactivate(new Date());
        productAdminInfoService.save(productAdminInfo);
    }

    public void successfulModerate(Product product){
        product.getProductAdminInfo().setModerate(true);
        product.setActive(true);
        productService.save(product);
    }

    public void unsuccessfulModerate(Product product, String adminMessage){
        deactivateProduct(product.getProductAdminInfo(), adminMessage);
        product.setActive(false);
        productService.save(product);
    }
}

package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.models.ProductAdminInfo;
import com.example.buysell.repositories.ProductAdminInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductAdminInfoService {
    private final ProductAdminInfoRepository productAdminInfoRepository;

    public void deactivateProduct(ProductAdminInfo productAdminInfo, String adminMessage){
        productAdminInfo.setModerate(true);
        productAdminInfo.setDeactivateReason(adminMessage);
        productAdminInfo.setDateDeactivate(new Date());
        productAdminInfoRepository.save(productAdminInfo);
    }

    public void saveProductAdminInfo(ProductAdminInfo productAdminInfo){
        productAdminInfoRepository.save(productAdminInfo);
    }
}

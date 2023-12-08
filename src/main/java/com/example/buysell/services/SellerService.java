package com.example.buysell.services;

import com.example.buysell.models.SellerData;
import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.repositories.SellerDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class SellerService {
    private final SellerDataRepository sellerDataRepository;

    private final UserService userService;

    public void becomeSeller(User user, SellerData sellerData){
        SellerData sellerDataDB;
        if(user.isSeller()){
            sellerDataDB = sellerDataRepository.findByUser(user);
        }
        else {
            sellerDataDB = new SellerData();
            user.getRoles().add(Role.ROLE_SELLER);
        }
        sellerDataDB.setAddress(sellerData.getAddress());
        sellerDataDB.setPhone(sellerData.getPhone());
        sellerDataDB.setContact(sellerData.getContact());
        sellerDataDB.setUser(user);
        user.setSellerData(sellerDataDB);
        sellerDataRepository.save(sellerDataDB);
        userService.save(user);
    }
}

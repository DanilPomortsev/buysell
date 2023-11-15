package com.example.buysell.repositories;

import com.example.buysell.models.SellerData;
import com.example.buysell.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDataRepository extends CrudRepository<SellerData, Long> {
    SellerData findByUser(User user);
}

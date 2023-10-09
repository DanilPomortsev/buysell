package com.example.buysell.repositories;

import com.example.buysell.models.Product;
import com.example.buysell.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u JOIN u.likes p WHERE p.id= :id")
//    List<User> findByUserLike(Long id);
    User findByEmail(String email);
}

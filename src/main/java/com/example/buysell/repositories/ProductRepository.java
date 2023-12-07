package com.example.buysell.repositories;

import com.example.buysell.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByTitle(String title);
    @Query("SELECT p FROM Product p JOIN p.userLike u WHERE u.id= :id")
    List<Product> findByUserLike(@Param("id") Long id);

    @Query("SELECT p FROM Product p JOIN p.productAdminInfo pa WHERE pa.moderate = false")
    List<Product> findProductsByModerationStatus();

    @Query("SELECT p FROM Product p WHERE p.active = true")
    List<Product> findActiveProducts();
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product_like (user_id, product_id) VALUES (:userId, :productId)", nativeQuery = true)
    void saveUserLike(@Param("userId") Long userId, @Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_like WHERE product_like.user_id = :user_id AND product_like.product_id = :product_id", nativeQuery = true)
    void deleteUserLike(@Param("user_id") Long userId, @Param("product_id") Long productId);

    @Query("SELECT COUNT(p) > 0 FROM Product p JOIN p.userLike u WHERE p.id = :productId AND u.id = :userId")
    boolean isLikeExists(@Param("userId") Long userId, @Param("productId") Long productId);
}

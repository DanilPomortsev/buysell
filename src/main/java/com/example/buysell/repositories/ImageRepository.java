package com.example.buysell.repositories;

import com.example.buysell.models.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends CrudRepository<Image, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Image i WHERE i.id = :imageId")
    void myDeleteById(@Param("imageId") Long imageId);

}

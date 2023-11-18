package com.example.buysell.repositories;

import com.example.buysell.models.Image;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    void deleteByIdIn(List<Long> ids);
}

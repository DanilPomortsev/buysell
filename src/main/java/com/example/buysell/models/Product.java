package com.example.buysell.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name= "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "city")
    private String city;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateOfCreated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "likes")
    private List<User> userLike;

    @Column(name = "active")
    private boolean active;

    @OneToOne(
            mappedBy = "product", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private ProductAdminInfo productAdminInfo;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }


    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public boolean isActive(){
        return active;
    }

    public boolean isModerating(){
        return productAdminInfo.isModerate();
    }

    public boolean isNotModerating(){
        return !productAdminInfo.isModerate();
    }

    public boolean isNotActive(){
        return !active;
    }

    @Override
    public String toString() {
        return "Product";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, city);
    }
}
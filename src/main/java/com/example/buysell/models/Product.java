package com.example.buysell.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name= "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Size(min= 5, max = 100,message = "Название товара должно обладать длиной от 5 до 100 символов")
    @Column(name = "title")
    private String title;

    @Size(min= 20, max = 10000,message = "Название товара должно обладать длиной от 20 до 10000 символов")
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Positive(message = "Должно быть положительным числом")
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

    @Column(name = "date_of_creating")
    @Temporal(TemporalType.DATE)
    private Date dateOfCreated;

    @Column(name = "date_of_last_changing")
    @Temporal(TemporalType.DATE)
    private Date dateOfLastChanging;

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
        dateOfLastChanging = new Date();
        dateOfCreated = new Date();
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
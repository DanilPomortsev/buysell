package com.example.buysell.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name="product_admin_info")
public class ProductAdminInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "moderate")
    private boolean moderate;

    @Column(name = "date_deactivate")
    private Date dateDeactivate;

    @Column(name = "deactivate_reason")
    private String deactivateReason;

    @Override
    public String toString() {
        return "ProductAdminInfo";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moderate, dateDeactivate, deactivateReason);
    }
}

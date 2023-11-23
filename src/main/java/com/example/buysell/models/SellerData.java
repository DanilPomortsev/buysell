package com.example.buysell.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "seller_data")
@Data
public class SellerData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sellerData_sequence")
    @SequenceGenerator(name = "sellerData_sequence", sequenceName = "sellerData_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Override
    public String toString() {
        return "SellerData";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, address, contact);
    }
}

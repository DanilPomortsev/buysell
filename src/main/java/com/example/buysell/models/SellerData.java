package com.example.buysell.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Pattern(regexp = "^\\+7\\d{10}$", message = "не коректно введён ноиер телефона. Пример: +79124959810")
    @Column(name = "phone")
    private String phone;

    @Size(min= 5, max = 100,message = "Адресс должен обладать длиной от 5 до 100 символов")
    @Column(name = "address")
    private String address;

    @Size(min= 5, max = 100,message = "Контактная ссылка должна обладать длиной от 5 до 100 символов")
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

package com.example.webServer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="product_id", unique = true)
    private String product_id = UUID.randomUUID().toString();

    @Column(name="product_name")
    private String product_name;

    @Column(name="price")
    private int price;

    @Column(name="quantity")
    private int quantity;
}
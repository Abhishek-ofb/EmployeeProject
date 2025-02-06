package com.example.webServer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @Column(name = "order_id",unique = true)
    private String order_id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product_id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer_id;

    @Column(name = "order_quantity")
    private int order_quantity;
}
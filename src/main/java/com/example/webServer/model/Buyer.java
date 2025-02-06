package com.example.webServer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="buyer")
public class Buyer {
    @Id
    @Column(name="buyer_id",unique = true)
    private String buyer_id = UUID.randomUUID().toString();

    @Column(name="buyer_name")
    private String buyer_name;

    @Column(name="buyer_email", unique = true)
    private String buyer_email ;

}

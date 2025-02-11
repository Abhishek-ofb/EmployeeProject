package com.example.webServer.repository.jpaRepo;

import com.example.webServer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product,String> {

}

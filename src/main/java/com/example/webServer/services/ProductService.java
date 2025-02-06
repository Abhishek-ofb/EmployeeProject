package com.example.webServer.services;

import com.example.webServer.model.Product;
import com.example.webServer.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public List<Product> getAllProduct(){
        return repo.findAll();
    }
    public Product getProductById(String id) {
        return repo.findById(id).orElse(null);
    }
    public Product  saveProduct(Product data){
        return repo.save(data);
    }

}

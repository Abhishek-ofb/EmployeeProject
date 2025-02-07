package com.example.webServer.services;

import com.example.webServer.model.Product;
import com.example.webServer.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }
    public Product getProductById(String id) {
        return productRepo.findById(id).orElse(null);
    }
    public Product  saveProduct(Product data){
        return productRepo.save(data);
    }

}

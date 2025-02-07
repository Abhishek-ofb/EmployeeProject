package com.example.webServer.controller;


import com.example.webServer.model.Product;
import com.example.webServer.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ProductController {


    @Autowired
    private ProductService productService;



    @GetMapping("/products")
    public ResponseEntity<?> getAllProductData() {
        System.out.println("Get all products called !!");

        List<Product> data = productService.getAllProduct();

        if (data == null || data.isEmpty()) {
            return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/products/{prodId}")
    public ResponseEntity<?> getProductById(@PathVariable String prodId){
        Product data = productService.getProductById(prodId);
        if (data == null) {
            return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product data) {
        System.out.println(data);
        Product prod = productService.saveProduct(data);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }



    @PutMapping("/products/{prodId}")
    public ResponseEntity<?> increaseStocks(@PathVariable String prodId){
        Product prod = productService.getProductById(prodId);
        if(prod!=null){
            prod.setQuantity(prod.getQuantity()+1);
            productService.saveProduct(prod);
            return new ResponseEntity<>(prod,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No product Found",  HttpStatus.NOT_FOUND);
        }

    }

}

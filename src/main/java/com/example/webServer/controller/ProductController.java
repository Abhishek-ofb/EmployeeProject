package com.example.webServer.controller;


import com.example.webServer.model.Product;
import com.example.webServer.model.ProductDocument;
import com.example.webServer.services.ProductDocumentService;
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
    @Autowired
    private ProductDocumentService productDocumentService;


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

    @GetMapping("/searchProducts")
    public ResponseEntity<?> searchProductByName(@RequestParam String searchParam){
        List<ProductDocument> response= productDocumentService.searchByName(searchParam);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/filterData")
    public ResponseEntity<?> filterProducts(
            @RequestParam(required = false) String searchParam,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer quantity
    ){
        List<ProductDocument> response = productDocumentService.filterData(
                searchParam,
                minPrice != null ? minPrice : 0,
                maxPrice != null ? maxPrice : Integer.MAX_VALUE,
                quantity != null ? quantity : 0
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/products/{prodId}")
    public ResponseEntity<?> increaseStocks(@PathVariable String prodId){
        Product response = productService.incrementProductQuantity(prodId);
        if(response!=null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No product Found",  HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/update/{prodId}")
    public ResponseEntity<?> updateProduct(@PathVariable String prodId,@RequestBody Product productData) {
        Product response = productService.updateProductById(prodId, productData);
        if(response!=null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
         return new ResponseEntity<>("No data Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products/{prodId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String prodId){
        boolean response = productService.deleteProduct(prodId);
        if(response){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }

}

package com.example.webServer.services;

import com.example.webServer.model.Product;
import com.example.webServer.model.ProductDocument;
import com.example.webServer.repository.jpaRepo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductDocumentService productDocumentService;

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public Product getProductById(String id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product saveProduct(Product data) {
        productDocumentService.saveData(data);
        return productRepo.save(data);
    }
    @Transactional
    public Product updateProductById(String prodId, Product data) {
        try {
            // Fetch product from database
            System.out.println(data);
            Product prodData = productRepo.findById(prodId).orElse(null);

            if (prodData != null) {

                prodData.setQuantity(data.getQuantity());
                prodData.setPrice(data.getPrice());
                prodData.setProduct_name(data.getProduct_name());

                ProductDocument elasticProd = productDocumentService.updateProductById(
                        prodId, data.getProduct_name(), data.getPrice(), data.getQuantity()
                );
                if (elasticProd == null) {
                    throw new Exception("Data not found in Elasticsearch repository!");
                }

                return productRepo.save(prodData);
            } else {
                throw new Exception("No product found with ID: " + prodId);
            }
        } catch (Exception e) {
            System.err.println("Error updating product: " + e.getMessage());
            return null;
        }
    }


    public Product incrementProductQuantity(String prodId){
        Product prod = productRepo.findById(prodId).orElse(null);
        if(prod!=null){
            prod.setQuantity(prod.getQuantity()+1);
            return  updateProductById(prodId,prod);
        }
        else{
            return  null;
        }
    }
    @Transactional
    public boolean deleteProduct(String prodId) {
        try {
            productRepo.deleteById(prodId);
            productDocumentService.deleteProduct(prodId);
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
}

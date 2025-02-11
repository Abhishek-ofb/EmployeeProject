package com.example.webServer.services;

import com.example.webServer.model.Product;
import com.example.webServer.model.ProductDocument;
import com.example.webServer.repository.elasticRepo.ProductElasticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductDocumentService {
    @Autowired
    private ProductElasticRepo productElasticRepo;

    public void saveData(Product data){
        productElasticRepo.save(new ProductDocument(data.getProduct_id(),data.getProduct_name(), data.getPrice(),
                data.getQuantity()));
    }
    public List<ProductDocument> searchByName(String searchValue) {
        return productElasticRepo.findByProductNameContains(searchValue);
    }
    public List<ProductDocument> searchByPrice(int minPrice, int maxPrice) {
        List<ProductDocument> prodData;
        if (minPrice != 0 && maxPrice != 0) {
            prodData = productElasticRepo.findByPriceBetween(minPrice, maxPrice);
        } else if (minPrice != 0) {
            prodData = productElasticRepo.findByPriceGreaterThan(minPrice);
        } else {
            prodData = productElasticRepo.findByPriceLessThan(maxPrice);
        }
        return prodData;
    }
    public List<ProductDocument> filterData(String searchParam, int minPrice, int maxPrice, int quantity) {

        System.out.println("product document services ..");
        System.out.println("maxPrice: " + maxPrice + ", minPrice: " + minPrice + ", searchParam: " + searchParam);

        List<ProductDocument> response = productElasticRepo.findByProductNameAndPriceRange(searchParam, minPrice,
                maxPrice);
        System.out.println(response);
        return response;
    }

    public ProductDocument updateProductById(String productId
        , String productName, Integer price , Integer quantity
    ){
        ProductDocument response = productElasticRepo.findById(productId).orElse(null);
        if(response!=null){
            if(productName!=null){
                response.setProductName(productName);
            }
            if(price!=null){
                response.setPrice(price);
            }
            if(quantity!=null){
                response.setQuantity(quantity);
            }
            return productElasticRepo.save(response);
        }
        return null;
    }
    public void deleteProduct(String prodId){
        productElasticRepo.deleteById(prodId);
    }
}
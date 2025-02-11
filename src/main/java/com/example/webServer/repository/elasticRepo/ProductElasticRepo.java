package com.example.webServer.repository.elasticRepo;

import com.example.webServer.model.ProductDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductElasticRepo extends ElasticsearchRepository<ProductDocument, String> {

    List<ProductDocument> findByProductNameContains(String s);
    List<ProductDocument> findByPriceBetween(int minPrice, int maxPrice);
    List<ProductDocument> findByPriceGreaterThan(int minPrice);
    List<ProductDocument> findByPriceLessThan(int maxPrice);


    @Query("""
    {
        "bool": {
            "must": [
              { "wildcard": { "productName.keyword": "*?0*" } }
            ],
            "filter": [
                { "range": { "price": { "gte": ?1, "lte": ?2 } } }
            ]
        }
    }
""")
    List<ProductDocument> findByProductNameAndPriceRange(String productName, int minPrice, int maxPrice);

}

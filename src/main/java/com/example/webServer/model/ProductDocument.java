package com.example.webServer.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products") // Define the Elasticsearch index
public class ProductDocument {

    @Id
    private String product_id;

    @Field(type = FieldType.Text, name = "productName")
    private String productName;

    @Field(type = FieldType.Integer, name = "price")
    private int price;

    @Field(type = FieldType.Integer, name="quantity")
    private int quantity;
}

package com.example.webServer.services;


import com.example.webServer.model.Buyer;
import com.example.webServer.model.Orders;
import com.example.webServer.model.Product;
import com.example.webServer.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private  OrderRepo orderRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private BuyerService buyerService;

    public List<Orders> getAllOrder(){
        return orderRepo.findAll();
    }
    public Orders getOrderData(String id){
        return  orderRepo.findById(id).orElse(null);
    }

    public  Orders saveOrder(Orders data){
        return orderRepo.save(data);
    }

    public Orders createOrder(String productId, String buyerId, int requiredQuantity){

        Buyer buyerData= buyerService.GetBuyer(buyerId);
        if(buyerData==null){
            return null;
        }
        Product productData= productService.getProductById(productId);
        if(productData==null || productData.getQuantity()<requiredQuantity){
            return  null;
        }
        Orders orderData= new Orders();
        orderData.setOrder_quantity(requiredQuantity);
        orderData.setBuyer_id(buyerData);
        orderData.setProduct_id(productData);
        this.saveOrder(orderData);

        productData.setQuantity(productData.getQuantity()-requiredQuantity);
        productService.saveProduct(productData);

        return  orderData;
    }

}

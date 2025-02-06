package com.example.webServer.controller;


import com.example.webServer.model.Orders;
import com.example.webServer.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){
        System.out.println("get all orders called!!");
        List<Orders> orderData = orderService.getAllOrder();
        if(orderData==null){
            return new ResponseEntity<>("Data  Not Found ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderData, HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId) {
        Orders data = orderService.getOrderData(orderId);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Found ", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/orders/{productId}/buyer/{buyerId}/order")
    public ResponseEntity<?> createOrder(
            @PathVariable String productId,
            @PathVariable String buyerId,
            @RequestParam int requiredQuantity
    ) {
        System.out.println(productId + buyerId );
        System.out.println(requiredQuantity);
        Orders orderData  = orderService.createOrder(productId, buyerId, requiredQuantity);
        if (orderData == null) {
            return new ResponseEntity<>(" data not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderData, HttpStatus.CREATED);
    }
}
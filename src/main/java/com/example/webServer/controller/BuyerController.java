package com.example.webServer.controller;

import com.example.webServer.model.Buyer;
import com.example.webServer.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BuyerController {

    @Autowired
    private  BuyerService buyerService;

    @PostMapping("/buyer")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer BuyerInfo){
        System.out.println("creating buyer data");
        Buyer data = buyerService.CreateBuyer(BuyerInfo);
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<?> getBuyerData(@PathVariable String buyerId){
        System.out.println("get Buyer data");
        Buyer data = buyerService.GetBuyer(buyerId);
        if(data!=null){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}

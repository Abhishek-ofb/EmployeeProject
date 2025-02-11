package com.example.webServer.services;


import com.example.webServer.model.Buyer;
import com.example.webServer.repository.jpaRepo.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepo buyerRepo;

    public Buyer CreateBuyer(Buyer data){
        return buyerRepo.save(data);
    }

    public Buyer GetBuyer(String id){
        return buyerRepo.findById(id).orElse(null);
    }
}

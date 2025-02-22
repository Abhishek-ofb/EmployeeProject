package com.example.webServer.repository.jpaRepo;

import com.example.webServer.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyerRepo extends JpaRepository<Buyer, String> {
}

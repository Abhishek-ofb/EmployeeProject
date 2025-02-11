package com.example.webServer.repository.jpaRepo;

import com.example.webServer.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends JpaRepository<Orders, String> {

}

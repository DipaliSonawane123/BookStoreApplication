package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Order;
import org.hibernate.boot.jaxb.mapping.spi.JaxbPostLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
}

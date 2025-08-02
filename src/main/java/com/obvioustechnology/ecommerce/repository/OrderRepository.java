package com.obvioustechnology.ecommerce.repository;

import com.obvioustechnology.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

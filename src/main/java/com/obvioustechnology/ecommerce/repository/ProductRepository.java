package com.obvioustechnology.ecommerce.repository;

import com.obvioustechnology.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

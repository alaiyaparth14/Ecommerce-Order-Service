package com.obvioustechnology.ecommerce.service;

import com.obvioustechnology.ecommerce.dto.ProductRequest;
import com.obvioustechnology.ecommerce.dto.ProductResponse;
import com.obvioustechnology.ecommerce.model.Product;
import com.obvioustechnology.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        Product saved = productRepository.save(product);

        return new ProductResponse(saved.getId(), saved.getName(), saved.getPrice(), saved.getStock());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductResponse(p.getId(), p.getName(), p.getPrice(), p.getStock()))
                .toList();
    }


}

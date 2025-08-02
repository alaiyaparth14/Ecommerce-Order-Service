package com.obvioustechnology.ecommerce.controller;

import com.obvioustechnology.ecommerce.dto.OrderRequest;
import com.obvioustechnology.ecommerce.dto.OrderResponse;
import com.obvioustechnology.ecommerce.model.Order;
import com.obvioustechnology.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.obvioustechnology.ecommerce.dto.OrderMapper.toResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        try {
            Order order = orderService.placeOrder(request);
            OrderResponse response = toResponse(order, "Order placed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            OrderResponse errorResponse = new OrderResponse(null, null, null, null, "FAILED", null, e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}

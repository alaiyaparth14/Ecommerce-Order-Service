package com.obvioustechnology.ecommerce.dto;

import com.obvioustechnology.ecommerce.dto.OrderResponse;
import com.obvioustechnology.ecommerce.model.Order;

public class OrderMapper {

    public static OrderResponse toResponse(Order order, String message) {
        return new OrderResponse(
                order.getId(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getOrderTime().toString(),
                message
        );
    }
}

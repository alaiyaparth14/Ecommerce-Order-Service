package com.obvioustechnology.ecommerce.service;

import com.obvioustechnology.ecommerce.dto.OrderRequest;
import com.obvioustechnology.ecommerce.enums.PaymentStatus;
import com.obvioustechnology.ecommerce.exception.CustomException;
import com.obvioustechnology.ecommerce.model.Order;
import com.obvioustechnology.ecommerce.model.Product;
import com.obvioustechnology.ecommerce.repository.OrderRepository;
import com.obvioustechnology.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrderSuccess() {
        // Arrange
        Product product = new Product(1L, "Laptop", 50000.0, 10, null);
        OrderRequest request = new OrderRequest(1L, 2);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Order order = orderService.placeOrder(request);

        // Assert
        assertNotNull(order);
        assertEquals(2, order.getQuantity());
        assertEquals(100000.0, order.getTotalPrice());
        assertEquals(PaymentStatus.SUCCESS, order.getStatus());
        assertEquals(8, product.getStock()); // stock should reduce
    }

    @Test
    public void testPlaceOrderProductNotFound() {
        // Arrange
        OrderRequest request = new OrderRequest(99L, 1);
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomException.class, () -> orderService.placeOrder(request));
    }

    @Test
    public void testPlaceOrderOutOfStock() {
        // Arrange
        Product product = new Product(1L, "Laptop", 50000.0, 1, null);
        OrderRequest request = new OrderRequest(1L, 5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act & Assert
        assertThrows(CustomException.class, () -> orderService.placeOrder(request));
    }
}

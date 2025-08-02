package com.obvioustechnology.ecommerce.service;

import com.obvioustechnology.ecommerce.dto.OrderRequest;
import com.obvioustechnology.ecommerce.enums.PaymentStatus;
import com.obvioustechnology.ecommerce.exception.CustomException;
import com.obvioustechnology.ecommerce.model.Order;
import com.obvioustechnology.ecommerce.model.Product;
import com.obvioustechnology.ecommerce.repository.OrderRepository;
import com.obvioustechnology.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentService paymentService;

    public Order placeOrder(OrderRequest orderRequest){
      Product product =  productRepository.findById(orderRequest.getProductId()).
                orElseThrow(()->new CustomException("Product Not Found"));
     if (orderRequest.getQuantity()<=0){
         throw new CustomException("Quantity must be grater than zero");
     }
     if (product.getStock()<orderRequest.getQuantity()){
         throw new CustomException("Insufficient stock for product : " + product.getName());
     }

     double totalAmout = product.getPrice()* orderRequest.getQuantity();
     PaymentStatus status = paymentService.processPayment(totalAmout);
      if (status==PaymentStatus.FAILED){
          throw new CustomException("Payment Failed");
      }
      product.setStock(product.getStock()-orderRequest.getQuantity());
      productRepository.save(product);

      Order order = new Order();
      order.setProduct(product);
      order.setQuantity(orderRequest.getQuantity());
      order.setTotalPrice(totalAmout);
      order.setStatus("CONFIRMED");
      order.setOrderTime(LocalDateTime.now());

      return orderRepository.save(order);
    }
}

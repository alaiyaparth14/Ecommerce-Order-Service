package com.obvioustechnology.ecommerce.service;

import com.obvioustechnology.ecommerce.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

     public PaymentStatus processPayment(double amount){

         if (amount<=0){
             return PaymentStatus.FAILED;
         }
         return PaymentStatus.SUCCESS;
     }
}

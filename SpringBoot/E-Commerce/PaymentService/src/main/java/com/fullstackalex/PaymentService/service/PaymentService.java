package com.fullstackalex.PaymentService.service;

import com.fullstackalex.PaymentService.model.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentResponse processPayment(Long orderId) {
        boolean success = Math.random() > 0.2; // 80% éxito
        return new PaymentResponse(orderId, success ? "SUCCESS" : "FAILED");
    }
}

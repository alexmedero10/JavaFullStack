package com.fullstackalex.PaymentService.controller;

import com.fullstackalex.PaymentService.model.PaymentRequest;
import com.fullstackalex.PaymentService.model.PaymentResponse;
import com.fullstackalex.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.processPayment(request.getOrderId()));
    }
}

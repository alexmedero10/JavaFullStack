package com.fullstackalex.PaymentService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponse {

    private Long orderId;
    private String status;

}

package com.fullstackalex.OrderService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {

    private Long productId;
    private int quantity;

}

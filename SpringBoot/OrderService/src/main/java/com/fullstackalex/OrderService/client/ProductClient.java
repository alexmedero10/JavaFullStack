package com.fullstackalex.OrderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ProductService")
public interface ProductClient {
    @GetMapping("/products/{id}/availability")
    boolean isAvailable(@PathVariable("id") Long productId, @RequestParam("quantity") int quantity);
}

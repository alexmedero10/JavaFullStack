package com.fullstackalex.OrderService.service;

import com.fullstackalex.OrderService.client.ProductClient;
import com.fullstackalex.OrderService.model.*;
import com.fullstackalex.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        List<OrderItem> items = new ArrayList<>();

        for (ItemRequest itemReq : request.getItems()) {
            if (!productClient.isAvailable(itemReq.getProductId(), itemReq.getQuantity())) {
                throw new RuntimeException("Producto no disponible: " + itemReq.getProductId());
            }
            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductId());
            item.setQuantity(itemReq.getQuantity());
            items.add(item);
        }

        order.setItems(items);
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}

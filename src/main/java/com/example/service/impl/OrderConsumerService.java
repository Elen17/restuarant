package com.example.service.impl;

import com.example.entity.Order;
import com.example.repository.OrderRepository;
import com.example.service.KitchenOrderService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {
    private final KitchenOrderService kitchenOrderService;

    public OrderConsumerService(KitchenOrderService kitchenOrderService) {
        this.kitchenOrderService = kitchenOrderService;
    }

    @KafkaListener(topics = "orders", groupId = "order_group")
    public void consumeOrder(Order order) {
        this.kitchenOrderService.createKitchenOrder(order);
    }

}

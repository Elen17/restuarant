package com.example.service;

import com.example.entity.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderDeliveryProducerService {
    private static final String TOPIC = "order_delivery";

    private final KafkaTemplate<Long, Order> kafkaTemplate;

    public OrderDeliveryProducerService(KafkaTemplate<Long, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderDelivery(Order order) {
        kafkaTemplate.send(TOPIC, order.getId(), order);
    }

}

package com.example.service;

import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderDeliveryProducerService {
    private static final String TOPIC = "order_delivery";

    @Autowired
    private KafkaTemplate<Long, Order> kafkaTemplate;

    public void sendOrderDelivery(Order order) {
        kafkaTemplate.send(TOPIC, order.getId(), order);
    }

}

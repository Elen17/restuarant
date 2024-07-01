package com.example.service.impl;

import com.example.entity.Order;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {
    private static final String TOPIC = "orders";

    private final KafkaTemplate<Long, Order> kafkaTemplate;

    public OrderProducerService(KafkaTemplate<Long, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        final ProducerRecord<Long, Order> producerRecord = new ProducerRecord<>(TOPIC, order.getId(), order);

        kafkaTemplate.send(producerRecord);
    }
}

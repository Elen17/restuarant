package com.example.service.impl;

import com.example.entity.Order;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {
    private static final String TOPIC = "orders";

    @Autowired
    private KafkaTemplate<Long, Order> kafkaTemplate;

    public void sendOrder(Order order) {
        final ProducerRecord<Long, Order> producerRecord = new ProducerRecord<>(TOPIC, order.getId(), order);

        kafkaTemplate.send(producerRecord);
//        kafkaTemplate.send(TOPIC, order.getId(), order);
    }
}

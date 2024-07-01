package com.example.service;

import com.example.entity.Order;

import java.util.Map;

public interface OrderService {
    void registerOrder(Map<String, Object> order);
}

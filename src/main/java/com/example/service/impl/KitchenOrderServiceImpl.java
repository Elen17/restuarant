package com.example.service.impl;

import com.example.common.OrderStatus;
import com.example.entity.Order;
import com.example.repository.OrderRepository;
import com.example.service.KitchenOrderService;
import com.example.service.OrderDeliveryProducerService;
import org.springframework.stereotype.Service;

@Service
public class KitchenOrderServiceImpl implements KitchenOrderService {

    private final OrderRepository orderRepository;
    private final OrderDeliveryProducerService orderDeliveryProducerService;

    public KitchenOrderServiceImpl(OrderRepository orderRepository,
                                   OrderDeliveryProducerService orderDeliveryProducerService) {
        this.orderRepository = orderRepository;
        this.orderDeliveryProducerService = orderDeliveryProducerService;
    }

    @Override
    public void createKitchenOrder(Order kitchenOrder) {
        // TODO: Implement the logic to create a kitchen order

        kitchenOrder.setStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(kitchenOrder);

        // TODO : Send the order to the kitchen and then receive the confirmation from the kitchen that the order is ready to be delivered
        orderDeliveryProducerService.sendOrderDelivery(kitchenOrder);

    }
}

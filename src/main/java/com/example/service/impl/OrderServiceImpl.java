package com.example.service.impl;

import com.example.common.OrderStatus;
import com.example.entity.Item;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.repository.CustomerRepository;
import com.example.repository.ItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.service.KitchenOrderService;
import com.example.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderProducerService orderProducerService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;


    public OrderServiceImpl(OrderProducerService orderProducerService,
                            OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CustomerRepository customerRepository,
                            ItemRepository itemRepository) {
        this.orderProducerService = orderProducerService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerOrder(Map<String, Object> orderDetails) {
        Order order = new Order();
        Long customerId = Optional.of(((Integer) orderDetails.get("customerId")).longValue()).orElseThrow(() -> new NullPointerException("Customer id is required"));
        order.setCustomer(this.customerRepository.findById(customerId).orElseThrow(() -> new NullPointerException("Customer not found")));
        order.setOrderDate(Timestamp.valueOf((String) orderDetails.get("orderDate")));
        order.setTotalAmount((Double) orderDetails.get("totalAmount"));
//        order.setStatus(OrderStatus.NEW);
        List<OrderItem> orderItems = new ArrayList<>();
        ((List<Map<String, Object>>) orderDetails.get("items"))
                .forEach(item -> {
                    Integer currentItemId = Optional.of((Integer) item.get("id")).orElseThrow(() -> new NullPointerException("Item id is required"));
                    Item currentItem = this.itemRepository.findById(currentItemId).orElseThrow(() -> new NullPointerException("Item id is required"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setItem(currentItem);
                    orderItem.setQuantity((Integer) item.get("quantity"));
                    orderItem.setOrder(order);

//                    this.orderItemRepository.save(orderItem);
                    orderItems.add(orderItem);
                });
        order.setOrderItems(orderItems);
        this.orderRepository.save(order);
        orderProducerService.sendOrder(order);
    }

}

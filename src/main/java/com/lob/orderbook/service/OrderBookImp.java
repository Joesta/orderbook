package com.lob.orderbook.service;

import com.lob.orderbook.interfaces.IOrderBook;
import com.lob.orderbook.model.Order;
import com.lob.orderbook.repository.OrderBookRepo;

public class OrderBookImp implements IOrderBook {
    @Override
    public boolean addOrder(Order order) {
        return OrderBookRepo.getInstance().addOrder(order);
    }

    @Override
    public boolean deleteOrder(String orderId) {
        Order order = getOrderById(orderId);
        return OrderBookRepo.getInstance()
                .getAllOrders()
                .remove(order);
    }

    @Override
    public Order modifyOrder(String orderId, int quantity) {
        Order order = getOrderById(orderId);
        order.setQuantity(quantity);
        addOrder(order);
        return order;
    }

    private Order getOrderById(String orderId) {
        return OrderBookRepo.getInstance()
                .getAllOrders()
                .stream()
                .filter(order -> order.getId().equals(orderId))
                .findAny()
                .orElse(null);
    }
}

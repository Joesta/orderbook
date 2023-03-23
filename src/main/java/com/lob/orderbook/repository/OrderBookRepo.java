package com.lob.orderbook.repository;

import com.lob.orderbook.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderBookRepo {

    private static OrderBookRepo instance;
    private List<Order> orders = new ArrayList<>();

    private OrderBookRepo(){}

    public static OrderBookRepo getInstance() {
        if (instance == null) {
            instance = new OrderBookRepo();
        }

        return instance;
    }

    public synchronized boolean addOrder(Order order) {
        return orders.add(order);
    }

    public boolean deleteOrder(Order order) {
        return orders.remove(order);
    }

    public List<Order> getAllOrders() {
        return this.orders;
    }
}

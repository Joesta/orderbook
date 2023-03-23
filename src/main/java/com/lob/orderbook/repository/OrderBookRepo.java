package com.lob.orderbook.repository;

import com.lob.orderbook.model.Order;

import java.util.LinkedList;

public class OrderBookRepo {

    private static OrderBookRepo instance;
    private LinkedList<Order> orders = new LinkedList<>();

    private OrderBookRepo(){}

    public static OrderBookRepo getInstance() {
        if (instance == null) {
            instance = new OrderBookRepo();
        }

        return instance;
    }

    public synchronized boolean addOrder(Order order) {
        Order existingOrder = checkIfOrderExist(order);
        boolean isOrderAdded = false;
        if (existingOrder != null) {
            // order has been modified
            orders.addLast(existingOrder);
            orders.remove(order);
            isOrderAdded = true;
        } else {
            isOrderAdded = orders.add(order);
        }

        return isOrderAdded;
    }

    public boolean deleteOrder(Order order) {
        return orders.remove(order);
    }

    public LinkedList<Order> getAllOrders() {
        return this.orders;
    }

    private Order checkIfOrderExist(Order order) {
       return getAllOrders()
                .stream()
                .filter(olderOrder -> olderOrder.getId().equals(order.getId()))
                .findFirst()
                .orElse(null);
    }
}

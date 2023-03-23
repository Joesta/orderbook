package com.lob.orderbook.model;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private String id;
    private String side;
    private int quantity;
    private double price;

    private OrderBuilder() {
    }

    private OrderBuilder(String id, String side, int quantity, double price) {
        this.id = id;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
    }

    public static Order buildOrder(final int i) {
        String orderId = "order#" + i; // UUID.randomUUID().toString();
        String side = "side#" + i;
        int quantity = i;
        double price = i * 2;

        return new OrderBuilder(orderId, side, quantity, price).build();
    }

    public static List<Order> buildOrders(final int noOfOrders) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < noOfOrders; i++) {
            orders.add(buildOrder(i));
        }

        return orders;
    }

    private Order build() {
        Order order = new Order();
        order.setId(id);
        order.setSide(side);
        order.setQuantity(quantity);
        order.setPrice(price);

        return order;
    }
}

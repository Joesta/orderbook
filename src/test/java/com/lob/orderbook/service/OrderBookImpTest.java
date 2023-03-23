package com.lob.orderbook.service;

import com.lob.orderbook.OrderbookApplicationTests;
import com.lob.orderbook.model.Order;
import com.lob.orderbook.model.OrderBuilder;
import com.lob.orderbook.repository.OrderBookRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class OrderBookImpTest extends OrderbookApplicationTests {

    private OrderBookImp orderBookImp;
    private Order order;
    private LinkedList<Order> orders;


    @Before
    public void setUp() throws Exception {
        System.out.println("Entering method: setup()");
        orderBookImp = new OrderBookImp();
        order = OrderBuilder.buildOrder(1);
        orders = OrderBuilder.buildOrders(5);
        OrderBookRepo.getInstance()
                .getAllOrders()
                .addAll(orders);
    }

    @After
    public void tearDown() throws Exception {
        orderBookImp = null;
        order = null;
        orders.clear();
        OrderBookRepo.getInstance().getAllOrders().clear();
    }

    @Test
    public void addOrder() {
        System.out.println("adding an order...)");
        boolean isOrderAdded = orderBookImp.addOrder(order);
        System.out.println("is order added ? " + isOrderAdded);

        List<Order> allOrders = OrderBookRepo.getInstance().getAllOrders();
        System.out.println("Available orders: " + allOrders.size());

    }

    @Test
    public void deleteOrder() {
        System.out.println("deleting order by id...");
        // order#1"
        boolean isOrderDeleted = orderBookImp.deleteOrder(order.getId());
        System.out.println("Order number # " + order.getId() + " has been deleted? " + isOrderDeleted);

        List<Order> allOrders = OrderBookRepo.getInstance().getAllOrders();
        System.out.println("Available orders: " + allOrders.size());
    }

    @Test
    public void modifyOrder() {
        System.out.println("modifying order by quantity ");
        System.out.println("existing order to modify " + orders.get(2));
        Order existingOrder = orders.get(2);
        int newQuantity = 10;

        Order modifiedOrder = orderBookImp.modifyOrder(existingOrder.getId(), newQuantity);
        System.out.println("modified order " + modifiedOrder.toString());

        List<Order> allOrders = OrderBookRepo.getInstance().getAllOrders();
        System.out.println("Available orders: " + allOrders.size());
    }
}
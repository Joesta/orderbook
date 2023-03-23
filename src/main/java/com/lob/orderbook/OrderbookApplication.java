package com.lob.orderbook;

import com.lob.orderbook.model.Order;
import com.lob.orderbook.repository.OrderBookRepo;
import com.lob.orderbook.service.OrderBookImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class OrderbookApplication {

    private static final OrderBookRepo repo = OrderBookRepo.getInstance();
    private static final OrderBookImp orderBookImp = new OrderBookImp();

    public static void main(String[] args) {
        SpringApplication.run(OrderbookApplication.class, args);
//		Order order = OrderBuilder.buildOrder(1);
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println(String.format("selected option = [ %d ]", option));

        switch (option) {
            case 1:
                addOrder();
                break;
            case 2:
                deleteOrder();
                break;
            case 3:
                modifyOrder();
                break;
            case 4:
                viewOrders();
                break;
            default:
                break;
        }
    }

    private static void addOrder() {
        // side ,quantity,price
        System.out.println("\n\nPlease enter bid type [ Buy or Sell ] : ");
        Scanner input = new Scanner(System.in);
        String bidType = input.nextLine();
        System.out.println("Please enter order quantity: ");
        int quantity = input.nextInt();
        System.out.println("Pleas enter order price: ");
        double price = input.nextDouble();

        Order order = new Order(bidType, quantity, price);
        boolean isOrderAdded = orderBookImp.addOrder(order);
        if (isOrderAdded) {
            System.out.println("\n\norder has been added");
        } else {
            System.out.println("\n\nFailed to add the order");
        }
    }

    private static void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nPlease enter the orderId: ");
        String orderId = scanner.nextLine();

        boolean isOrderDeleted = orderBookImp.deleteOrder(orderId);
        if (isOrderDeleted) {
            System.out.println("\n\norder has been deleted");
        } else {
            System.out.println("\n\nFailed to delete order " + orderId);
        }
    }

    private static void modifyOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nPlease enter the orderId: ");
        String orderId = scanner.nextLine();
        System.out.println("Please enter quantity for the order: ");
        int quantity = scanner.nextInt();

        Order order = orderBookImp.modifyOrder(orderId, quantity);
        if (order != null) {
            System.out.println("Order No# " + order.getId() + " has been modified");
        } else {
            System.out.println("Order No# " + orderId + " was either not found or failed to failed to modify");
        }
    }

    private static void viewOrders() {
        repo.getAllOrders().forEach(
                order -> {
                    System.out.println(order.toString() + "\n");
                }
        );
    }
}

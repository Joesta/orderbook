package com.lob.orderbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class OrderbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderbookApplication.class, args);
//		Order order = OrderBuilder.buildOrder(1);
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println(String.format("selected option = [ %d ]", option));


    }
}

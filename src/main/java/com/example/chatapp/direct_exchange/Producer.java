package com.example.chatapp.direct_exchange;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static com.example.chatapp.direct_exchange.Constant.*;

public class Producer {
    private DirectExchangeChannel channel;

    public void start() throws IOException, TimeoutException {
        // Create connection
        Connection connection = ConnectionManager.createConnection();

        // Create channel
        channel = new DirectExchangeChannel(connection, EXCHANGE_NAME);

        // Create direct exchange
        channel.declareExchange();

        // Create queues
        channel.declareQueues(USER1_QUEUE_NAME, USER2_QUEUE_NAME);

        // Binding queues with routing keys
        channel.performQueueBinding(USER1_QUEUE_NAME);
        channel.performQueueBinding(USER2_QUEUE_NAME);
    }

    public void send(String message) throws IOException {
        // Send message
        channel.publishMessage(message);
    }
//
//    public static void main(String[] args) throws IOException, TimeoutException {
//        start();
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            int chose = chose();
//            if(chose==0) return;
//            else if(chose==1) {
//                System.out.print("Input message: ");
//                String message = sc.nextLine();
//                send(message, DEV_ROUTING_KEY);
//            } else if(chose==2) {
//                System.out.print("Input message: ");
//                String message = sc.nextLine();
//                send(message, MANAGER_ROUTING_KEY);
//            } else if(chose==3) {
//                System.out.print("Input message: ");
//                String message = sc.nextLine();
//                send(message, GENERAL_ROUTING_KEY);
//            } else {
//                System.out.print("Input message: ");
//                String message = sc.nextLine();
//                send(message, DEV_ROUTING_KEY);
//                send(message, MANAGER_ROUTING_KEY);
//                send(message, GENERAL_ROUTING_KEY);
//            }
//        }
//    }
//
//    private static int chose() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("0. Close program!");
//        System.out.println("1. Send message to developer");
//        System.out.println("2. Send message to manager");
//        System.out.println("3. Send message to general");
//        System.out.println("4. Send message to all");
//        System.out.print("Please chose: ");
//        int option;
//        do {
//            option = sc.nextInt();
//        } while (option<0 && option>3);
//        return option;
//    }
}

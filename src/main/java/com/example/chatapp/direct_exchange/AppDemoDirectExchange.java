package com.example.chatapp.direct_exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.example.chatapp.direct_exchange.Constant.*;


public class AppDemoDirectExchange {

    public static void main(String[] args) throws IOException, TimeoutException {
        // Create producer
        Producer producer = new Producer();
        producer.start();

        // Publish some message
        producer.send("This message for message 1");
        producer.send("This message for message 2");

        Consumer consumer = new Consumer();
        consumer.start();
        consumer.subscribe();
    }
}

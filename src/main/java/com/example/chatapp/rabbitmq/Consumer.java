package com.example.chatapp.rabbitmq;


import com.example.chatapp.controller.Controller;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private ExchangeChanel channel;

    private String EXCHANGE_NAME = "CHATAPP";

    public void start(String queue) throws IOException, TimeoutException {
        // Create connection
        Connection connection = ConnectionManager.createConnection();

        // Create channel
        channel = new ExchangeChanel(connection, EXCHANGE_NAME);

        // Create direct exchange
        channel.declareExchange();

        // Create queues
        channel.declareQueues(queue);

        // Binding queues with routing keys
        channel.performQueueBinding(queue);
    }

    public void subscribe(String queue, Controller controller) throws IOException {
        // Subscribe message
        channel.subscribeMessage(queue, controller);
    }

}

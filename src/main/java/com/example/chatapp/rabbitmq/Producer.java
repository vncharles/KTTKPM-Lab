package com.example.chatapp.rabbitmq;


import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import static com.example.chatapp.rabbitmq.Constant.*;

public class Producer {
    private ExchangeChanel channel;

    public void start() throws IOException, TimeoutException {
        // Create connection
        Connection connection = ConnectionManager.createConnection();

        // Create channel
        channel = new ExchangeChanel(connection, EXCHANGE_NAME);

        // Create direct exchange
        channel.declareExchange();

        // Create queues
        channel.declareQueues(USER1_QUEUE_NAME);

        // Binding queues with routing keys
        channel.performQueueBinding(USER1_QUEUE_NAME);
    }

    public void send(String message) throws IOException {
        // Send message
        channel.publishMessage(message);
    }
}

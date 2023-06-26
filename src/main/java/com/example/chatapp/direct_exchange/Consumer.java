package com.example.chatapp.direct_exchange;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.example.chatapp.direct_exchange.Constant.*;


public class Consumer {

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

    public void subscribe() throws IOException {
        // Subscribe message
        channel.subscribeMessage(USER1_QUEUE_NAME);
        channel.subscribeMessage(USER2_QUEUE_NAME);
    }

}

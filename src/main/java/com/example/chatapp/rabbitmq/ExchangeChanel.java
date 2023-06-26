package com.example.chatapp.rabbitmq;

import com.example.chatapp.controller.Controller;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class ExchangeChanel {
    private String exchangeName;
    private Channel channel;
    private Connection connection;

    public ExchangeChanel(Connection connection, String exchangeName) throws IOException {
        this.exchangeName = exchangeName;
        this.connection = connection;
        this.channel = connection.createChannel();
    }

    public void declareExchange() throws IOException {
        // exchangeDeclare( exchange, builtinExchangeType, durable)
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT, true);
    }

    public void declareQueues(String ...queueNames) throws IOException {
        for (String queueName : queueNames) {
            // queueDeclare  - (queueName, durable, exclusive, autoDelete, arguments)
            channel.queueDeclare(queueName, true, false, false, null);
        }
    }

    public void performQueueBinding(String queueName) throws IOException {
        // Create bindings - (queue, exchange, routingKey)
        channel.queueBind(queueName, exchangeName, "");
    }

    public void subscribeMessage(String queueName, Controller controller) throws IOException {
        // basicConsume - ( queue, autoAck, deliverCallback, cancelCallback)
        channel.basicConsume(queueName, true, ((consumerTag, message) -> {
            controller.setChat("[Received] [" + queueName + "]: " + new String(message.getBody()));
        }), consumerTag -> {
            System.out.println(consumerTag);
        });
    }

    public void publishMessage(String queueName, String message, Controller controller) throws IOException {
        // basicPublish - ( exchange, routingKey, basicProperties, body)
        controller.setChat("[Send]: [" + queueName + "]: " + message);
        channel.basicPublish(exchangeName, "", null, message.getBytes());
    }
}

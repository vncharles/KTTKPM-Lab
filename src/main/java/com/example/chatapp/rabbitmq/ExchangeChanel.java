package com.example.chatapp.rabbitmq;

import com.example.chatapp.controller.Controller;
import com.example.chatapp.entity.Message;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

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
        channel.basicConsume(queueName, true, ((consumerTag, delivery) -> {
            Message message = SerializationUtils.deserialize(delivery.getBody());
            controller.setChat(message);
        }), consumerTag -> {
            System.out.println(consumerTag);
        });
    }

    public void publishMessage(Message message, Controller controller) throws IOException {
        // basicPublish - ( exchange, routingKey, basicProperties, body)
        channel.basicPublish(exchangeName, "", null, SerializationUtils.serialize(message));
    }
}

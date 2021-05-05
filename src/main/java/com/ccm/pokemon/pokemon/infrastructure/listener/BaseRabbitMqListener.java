package com.ccm.pokemon.pokemon.infrastructure.listener;

import com.rabbitmq.client.*;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public abstract class BaseRabbitMqListener {

    private final Logger LOGGER = Logger.getLogger(RabbitMqEventListener.class);

    protected Connection connection;
    protected Channel channel;

    protected void configureListener(String queue, String host) throws IOException, TimeoutException {
        LOGGER.info("RabbitMqListener about to be configured");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queue, true, false, false, null);
        DeliverCallback deliverCallback = this::onMessage;
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});
    }

    abstract void onMessage (String consumerTag, Delivery delivery) throws UnsupportedEncodingException;
}

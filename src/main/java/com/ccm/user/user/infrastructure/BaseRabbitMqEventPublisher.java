package com.ccm.user.user.infrastructure;

import com.ccm.pokemon.pokemon.infrastructure.listener.BaseRabbitMqListener;
import com.ccm.user.user.domain.interfaces.EventPublisher;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class BaseRabbitMqEventPublisher {

    private final Logger LOGGER = Logger.getLogger(BaseRabbitMqListener.class);

    protected Connection connection;
    protected Channel channel;

    protected void configurePublisher(String queue, String host) throws IOException, TimeoutException {
        LOGGER.info("RabbitMqEventPublisher is about to be configured");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queue, true, false, false, null);
    }


}

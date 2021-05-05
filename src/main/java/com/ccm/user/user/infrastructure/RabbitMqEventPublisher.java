package com.ccm.user.user.infrastructure;

import com.ccm.pokemon.pokemon.infrastructure.listener.BaseRabbitMqListener;
import com.ccm.user.user.domain.interfaces.EventPublisher;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
@Named("RabbitMq")
public class RabbitMqEventPublisher extends BaseRabbitMqEventPublisher implements EventPublisher {

    private final Logger LOGGER = Logger.getLogger(RabbitMqEventPublisher.class);

    @ConfigProperty(name = "rabbit.queue")
    String RABBIT_QUEUE = "pokemon-in";
//    private final static String RABBIT_QUEUE = "pokemon-in";

    @ConfigProperty(name = "rabbit.host")
    String RABBIT_HOST = "localhost";
//    private final static String RABBIT_HOST = "localhost";

    void onStart(@Observes StartupEvent ev) throws IOException, java.util.concurrent.TimeoutException {
        LOGGER.info("RabbitMqEventPublisher is starting...");
        configurePublisher(RABBIT_QUEUE, RABBIT_HOST);
    }

    @Override
    public void publish(String message) throws IOException {
        channel.basicPublish("", RABBIT_QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
    }
}

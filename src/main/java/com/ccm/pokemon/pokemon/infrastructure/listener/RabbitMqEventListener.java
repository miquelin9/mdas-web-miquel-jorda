package com.ccm.pokemon.pokemon.infrastructure.listener;

import com.ccm.pokemon.pokemon.application.dto.PokemonDto;
import com.ccm.pokemon.pokemon.application.useCases.FavouritePokemonAddedNotifierUseCase;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.infrastructure.parsers.JsonToPokemonDTOParser;
import com.rabbitmq.client.*;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Startup
@Singleton
public class RabbitMqEventListener extends BaseRabbitMqListener {

    private final Logger LOGGER = Logger.getLogger(RabbitMqEventListener.class);

    private final static String CHARSET_NAME = "UTF-8";

    @ConfigProperty(name = "rabbit.queue")
    String RABBIT_QUEUE = "pokemon-in";
//    private final static String RABBIT_QUEUE = "pokemon-in";
    @ConfigProperty(name = "rabbit.host")
    String RABBIT_HOST = "localhost";
//    private final static String RABBIT_HOST = "localhost";

    @Inject
    JsonToPokemonDTOParser jsonToPokemonDTOParser;
    @Inject
    FavouritePokemonAddedNotifierUseCase favouritePokemonAddedNotifierUseCase;


    void onStart(@Observes StartupEvent ev) throws IOException, java.util.concurrent.TimeoutException {
        LOGGER.info("RabbitMqEventListener is starting...");
        configureListener(RABBIT_QUEUE, RABBIT_HOST);
    }

    @Override
    protected void onMessage(String consumerTag, Delivery delivery) throws UnsupportedEncodingException {
        LOGGER.info("Message received!");

        try {
            String message = new String(delivery.getBody(), CHARSET_NAME);
            JSONObject obj = (JSONObject) jsonToPokemonDTOParser.jsonParser.parse(message);
            PokemonDto pokemonDto = jsonToPokemonDTOParser.jsonToPokemonDto(obj);
            favouritePokemonAddedNotifierUseCase.addFavouritePokemonCounter(pokemonDto);
        } catch (PokemonNotFoundException | TimeoutException | NetworkConnectionException |
                ClassCastException e) {
            LOGGER.error(e.getMessage());
        } catch (ParseException e) {
            if (Objects.isNull(e.getMessage()))
                LOGGER.error("Parsing exception: could not parse message and returned null");
            else
                LOGGER.error(e.getMessage());
        } catch (UnknownException e) {
            LOGGER.error("Unknown exception: " + e.getMessage());
        }
    }
}

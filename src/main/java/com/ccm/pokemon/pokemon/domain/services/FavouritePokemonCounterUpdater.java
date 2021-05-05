package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import org.jboss.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FavouritePokemonCounterUpdater {

    private final Logger LOGGER = Logger.getLogger(FavouritePokemonCounterUpdater.class);

    @Inject
    PokemonSaver pokemonSaver;

    public void execute(Pokemon pokemon) {
        LOGGER.info("Trying to update pokemon '" + pokemon.getName().getName() + "' favourite counter");

        pokemon.addFavouriteCounter();
        pokemonSaver.persist(pokemon);

        LOGGER.info("Pokemon '" + pokemon.getName().getName() + "' favourite counter updated to "
                + pokemon.getFavouriteCounter().getFavouriteCounter());
    }
}

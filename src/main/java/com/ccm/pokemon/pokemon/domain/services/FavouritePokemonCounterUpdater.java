package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class FavouritePokemonCounterUpdater {

    @Inject
    PokemonSaver pokemonSaver;

    public void execute(Pokemon pokemon) {
        pokemon.addFavouriteCounter();

        pokemonSaver.persist(pokemon);
    }
}

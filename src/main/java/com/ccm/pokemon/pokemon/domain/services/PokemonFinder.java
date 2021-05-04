package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRetrieverClient;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class PokemonFinder {
    @Inject
    @Named("API")
    PokemonRetrieverClient pokemonRetrieverClient;

    @Inject
    @Named("InMemoryPokemon")
    PokemonRepository pokemonRepository;

    public Pokemon findPokemon(PokemonId pokemonId) throws PokemonNotFoundException, NetworkConnectionException, TimeoutException, UnknownException {
        if (pokemonRepository.exists(pokemonId)) {
            return pokemonRepository.find(pokemonId);
        }

        Pokemon pokemon = pokemonRetrieverClient.find(pokemonId);
        pokemonRepository.create(pokemon);
        return pokemon;
    }
}

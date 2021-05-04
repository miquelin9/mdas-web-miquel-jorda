package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class PokemonSaver {

    @Inject
    @Named("InMemoryPokemon")
    PokemonRepository pokemonRepository;

    public void persist (Pokemon pokemon) {
        if (pokemonRepository.exists(pokemon.getPokemonId())) {
            pokemonRepository.update(pokemon);
        } else {
            pokemonRepository.create(pokemon);
        }
    }

}

package com.ccm.pokemon.pokemonTypes.domain.exceptions;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}

package com.ccm.pokemon.pokemon.domain.exceptions;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}

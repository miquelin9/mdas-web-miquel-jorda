package com.ccm.pokemon.pokemonTypes.domain.valueObjects;

public class PokemonId {

    public PokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    private int pokemonId;

    @Override
    public String toString() {
        return String.valueOf(pokemonId);
    }
}

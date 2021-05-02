package com.ccm.pokemon.pokemon.domain.valueObjects;

import java.util.Objects;

public class PokemonType {
    private String pokemonType;

    public PokemonType(String pokemonType) {
        this.pokemonType = pokemonType;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonType that = (PokemonType) o;
        return pokemonType.equals(that.pokemonType);
    }
}

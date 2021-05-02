package com.ccm.pokemon.pokemon.domain.valueObjects;

public class PokemonId {

    public PokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    private int pokemonId;

    public int getPokemonId() {
        return pokemonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonId pokemonId1 = (PokemonId) o;
        return pokemonId == pokemonId1.pokemonId;
    }
}

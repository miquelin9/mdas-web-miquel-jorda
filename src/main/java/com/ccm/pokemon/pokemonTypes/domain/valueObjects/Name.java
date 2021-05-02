package com.ccm.pokemon.pokemonTypes.domain.valueObjects;

public class Name {
    public Name(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}

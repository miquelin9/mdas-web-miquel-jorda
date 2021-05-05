package com.ccm.user.user.application.dto;

public class PokemonDTO {

    private int pokemonId;

    public PokemonDTO(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public String toJson() {
        return "{ \"pokemonId\":" + this.pokemonId + " }";
    }
}

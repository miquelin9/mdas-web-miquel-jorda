package com.ccm.user.user.application.dto;

public class UserFavouritePokemonDTO {
    private int pokemonId;
    private int userId;

    public UserFavouritePokemonDTO(int pokemonId, int userId) {
        this.pokemonId = pokemonId;
        this.userId = userId;
    }

    public int getPokemonId() {
        return pokemonId;
    }
    public int getUserId() {
        return userId;
    }
}

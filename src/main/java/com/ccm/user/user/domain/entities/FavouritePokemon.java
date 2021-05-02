package com.ccm.user.user.domain.entities;

import com.ccm.user.user.domain.vo.FavouritePokemonId;

import java.util.Objects;

public class FavouritePokemon {

    public FavouritePokemon(FavouritePokemonId favouritePokemonId) {
        this.favouritePokemonId = favouritePokemonId;
    }

    private FavouritePokemonId favouritePokemonId;

    public FavouritePokemonId getFavouritePokemonId() {
        return favouritePokemonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouritePokemon that = (FavouritePokemon) o;
        return Objects.equals(favouritePokemonId, that.favouritePokemonId);
    }
}

package com.ccm.user.user.domain.aggregate;

import com.ccm.user.user.domain.entities.FavouritePokemon;
import com.ccm.user.user.domain.vo.FavouritePokemons;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.FavouritePokemonDoesNotExistException;

public class User {


    public User(UserName name, UserId userId) {
        this.name = name;
        this.userId = userId;
        this.favouritePokemons = new FavouritePokemons();
    }

    private UserName name;
    private UserId userId;
    private FavouritePokemons favouritePokemons;

    public UserId getUserId () {
        return this.userId;
    }

    public UserName getName() {
        return name;
    }

    public FavouritePokemons getFavouritePokemons() {
        return favouritePokemons;
    }

    public void addFavouritePokemon(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        this.favouritePokemons.addFavouritePokemonToList(pokemon);
    }

    public void removeFavouritePokemon(FavouritePokemon pokemon) throws FavouritePokemonDoesNotExistException {
        this.favouritePokemons.removeFavouritePokemonFromList(pokemon);
    }
}

package com.ccm.user.user.domain.vo;

import com.ccm.user.user.domain.entities.FavouritePokemon;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.FavouritePokemonDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class FavouritePokemons {

    public FavouritePokemons() {
        favouritePokemonList = new ArrayList<>();
    }

    private final List<FavouritePokemon> favouritePokemonList;

    public void addFavouritePokemonToList(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        existsGuard(pokemon);
        favouritePokemonList.add(pokemon);
    }

    public void removeFavouritePokemonFromList(FavouritePokemon pokemon) throws FavouritePokemonDoesNotExistException {
        notExistsGuard(pokemon);
        OptionalInt index = IntStream.range(0, favouritePokemonList.size())
                .filter(i -> pokemon.getFavouritePokemonId().equals(favouritePokemonList.get(i).getFavouritePokemonId()))
                .findFirst();
        favouritePokemonList.remove(index.getAsInt());
    }

    public List<FavouritePokemon> getFavouritePokemonList() {
        return favouritePokemonList;
    }

    private void existsGuard(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = pokemon.getFavouritePokemonId();

        if (favouritePokemonList.stream().anyMatch(favouritePokemon -> favouritePokemon.getFavouritePokemonId().equals(pokemonId))) {
            throw new FavouritePokemonAlreadyExistsException("The user already has the pokemon " + pokemonId.getPokemonId() + " as favourite");
        }
    }

    private void notExistsGuard(FavouritePokemon pokemon) throws FavouritePokemonDoesNotExistException {
        FavouritePokemonId pokemonId = pokemon.getFavouritePokemonId();

        if (!favouritePokemonList.stream().anyMatch(favouritePokemon -> favouritePokemon.getFavouritePokemonId().equals(pokemonId))) {
            throw new FavouritePokemonDoesNotExistException("The user doesn't have the pokemon " + pokemonId.getPokemonId() + " as favourite");
        }
    }

}

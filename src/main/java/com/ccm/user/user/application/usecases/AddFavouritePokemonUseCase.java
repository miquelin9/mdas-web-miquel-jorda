package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserFavouritePokemonDTO;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.services.AddFavouritePokemonToUser;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddFavouritePokemonUseCase {

    @Inject
    AddFavouritePokemonToUser addFavouritePokemonToUser;

    public void addFavouritePokemon (UserFavouritePokemonDTO user) throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemonId _pokemonId = new FavouritePokemonId(user.getPokemonId());
        UserId _userId = new UserId(user.getUserId());

        addFavouritePokemonToUser.execute(_pokemonId, _userId);
    }
}

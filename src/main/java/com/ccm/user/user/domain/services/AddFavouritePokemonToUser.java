package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.entities.FavouritePokemon;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddFavouritePokemonToUser {
    @Inject
    UserFinder userFinder;

    @Inject
    UserSaver userSaver;

    public void execute(FavouritePokemonId pokemonId, UserId userId) throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        User user = userFinder.findUser(userId);
        user.addFavouritePokemon(new FavouritePokemon(pokemonId));
        userSaver.saveUser(user);
    }
}

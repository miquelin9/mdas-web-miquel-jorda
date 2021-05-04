package com.ccm.pokemon.pokemon.domain.interfaces;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;

import java.util.List;

public interface PokemonRepository {

    Pokemon find(PokemonId pokemonId);

    Boolean exists(PokemonId pokemonId);

    void create(Pokemon pokemon);

    Pokemon update(Pokemon pokemon);
}

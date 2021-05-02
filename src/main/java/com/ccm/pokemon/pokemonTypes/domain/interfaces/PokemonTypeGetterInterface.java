package com.ccm.pokemon.pokemonTypes.domain.interfaces;

import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import org.json.simple.JSONObject;

public interface PokemonTypeGetterInterface {

    public JSONObject getPokemonTypeJsonByPokemonName(String pokemonName) throws PokemonNotFoundException;
}

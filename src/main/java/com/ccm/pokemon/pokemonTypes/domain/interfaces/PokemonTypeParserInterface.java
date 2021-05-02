package com.ccm.pokemon.pokemonTypes.domain.interfaces;

import com.ccm.pokemon.pokemonTypes.domain.aggregate.PokemonType;

import java.util.List;

public interface PokemonTypeParserInterface {

    public List<PokemonType> toPokemonTypeList (Object origin);
}

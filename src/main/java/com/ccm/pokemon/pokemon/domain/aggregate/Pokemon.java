package com.ccm.pokemon.pokemon.domain.aggregate;

import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonType;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonTypes;

import java.util.Objects;

public class Pokemon {

    public Pokemon(Name name, PokemonId pokemonId) {
        this.name = name;
        this.pokemonId = pokemonId;
        this.pokemonTypes = new PokemonTypes();
    }

    public Name getName() {
        return name;
    }

    public PokemonId getPokemonId() {
        return pokemonId;
    }

    public PokemonTypes getPokemonTypes() {
        return pokemonTypes;
    }

    private Name name;
    private PokemonId pokemonId;
    private PokemonTypes pokemonTypes;

    public void addPokemonType(PokemonType pokemonType) {
        this.pokemonTypes.addType(pokemonType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return name.equals(pokemon.name) && pokemonId.equals(pokemon.pokemonId) && pokemonTypes.equals(pokemon.pokemonTypes);
    }
}

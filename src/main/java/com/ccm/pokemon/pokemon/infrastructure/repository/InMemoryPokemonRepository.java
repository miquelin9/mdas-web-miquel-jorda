package com.ccm.pokemon.pokemon.infrastructure.repository;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@ApplicationScoped
@Named("InMemoryPokemon")
public class InMemoryPokemonRepository implements PokemonRepository {

    private List<Pokemon> inMemoryPokemons = new ArrayList<>();

    @Override
    public Pokemon find(PokemonId pokemonId) {
        return inMemoryPokemons.stream()
                .filter(x -> x.getPokemonId().equals(pokemonId)).findAny()
                .orElse(null);
    }

    @Override
    public Boolean exists(PokemonId pokemonId) {
        return inMemoryPokemons.stream()
                .anyMatch(x -> x.getPokemonId().equals(pokemonId));
    }

    @Override
    public void create(Pokemon pokemon) {
        inMemoryPokemons.add(pokemon);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        OptionalInt index = IntStream.range(0, inMemoryPokemons.size())
                .filter(i -> pokemon.getPokemonId().equals(inMemoryPokemons.get(i).getPokemonId()))
                .findFirst();

        inMemoryPokemons.set(index.getAsInt(), pokemon);
        return pokemon;
    }
}

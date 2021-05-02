package com.ccm.pokemon.pokemon.infrastructure;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonType;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class HttpPokemonRepositoryTest {
    @Inject
    PokemonRepository pokemonRepository;

    @Test
    public void shouldFindPokemon() throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        //Given
        PokemonId pokemonId = new PokemonId(1);
        Name pokemonName = new Name("bulbasaur");
        Pokemon pokemon = new Pokemon(pokemonName, pokemonId);
        pokemon.addPokemonType(new PokemonType("grass"));
        pokemon.addPokemonType(new PokemonType("poison"));

        //When
        Pokemon retrievedPokemon = pokemonRepository.find(pokemonId);

        //Then
        Assertions.assertEquals(pokemon, retrievedPokemon);
    }

    @Test
    public void shouldThrowPokemonNotFoundException() throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {
        //Given
        PokemonId pokemonId = new PokemonId(0);

        //When ("then" stage is also included because of the exception)
        assertThrows(
            com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException.class,
            () -> {
                pokemonRepository.find(pokemonId);
            }
        );
    }
}

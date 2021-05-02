package com.ccm.pokemon.pokemonTypes.infrastructure.controller;

import com.ccm.pokemon.pokemonTypes.application.GetPokemonTypeUseCase;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Model
@Path("/pokemon")
public class PokemonControllerOld {

    @Inject
    GetPokemonTypeUseCase getPokemonTypeUseCase;

    @GET
    @Path("/types")
    public String getTypes(@QueryParam("name") String name) {
        try {
            return getPokemonTypeUseCase.getPokemonTypeByPokemonName(name);
        } catch (PokemonNotFoundException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Unexpected error. " + e.getMessage();
        }
    }
}

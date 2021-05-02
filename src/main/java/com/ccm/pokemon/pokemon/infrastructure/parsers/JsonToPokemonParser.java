package com.ccm.pokemon.pokemon.infrastructure.parsers;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JsonToPokemonParser {
    public Pokemon castJsonToPokemon(JSONObject pokemonResponse) {
        JSONArray typesJson = (JSONArray) pokemonResponse.get("types");

        Pokemon pokemon = new Pokemon(
                new Name((String) pokemonResponse.get("name")),
                new PokemonId(((Long) pokemonResponse.get("id")).intValue())
        );
        for (int i = 0; i < typesJson.size(); i++) {
            PokemonType type = new PokemonType((String) ((JSONObject) ((JSONObject) typesJson.get(i)).get("type")).get("name"));
            pokemon.addPokemonType(type);
        }
        return pokemon;
    }
}

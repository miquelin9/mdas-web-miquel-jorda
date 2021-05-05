package com.ccm.pokemon.pokemon.infrastructure.parsers;

import com.ccm.pokemon.pokemon.application.dto.PokemonDto;
import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JsonToPokemonDTOParser {

    public JSONParser jsonParser = new JSONParser();

    public PokemonDto jsonToPokemonDto(JSONObject request) {
        return new PokemonDto(
                (int) request.get("pokemonId")
        );
    }
}

package com.ccm.pokemon.pokemon.infrastructure.parsers;

import com.ccm.pokemon.pokemon.application.dto.PokemonDto;
import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonType;
import io.smallrye.mutiny.streams.utils.Casts;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

@ApplicationScoped
public class JsonToPokemonDTOParser {

    public JSONParser jsonParser = new JSONParser();

    public PokemonDto jsonToPokemonDto(JSONObject request) {
        if (Objects.isNull(request))
            throw new ClassCastException("Can not cast null to Long");
        if (Objects.isNull(request.get("pokemonId")))
            throw new ClassCastException("pokemonId not found in message. Check if message has this property");
        return new PokemonDto(
                ((Long) request.get("pokemonId")).intValue()
        );
    }
}

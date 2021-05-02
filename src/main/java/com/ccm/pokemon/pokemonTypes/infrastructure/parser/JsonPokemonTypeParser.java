package com.ccm.pokemon.pokemonTypes.infrastructure.parser;

import com.ccm.pokemon.pokemonTypes.domain.aggregate.PokemonType;
import com.ccm.pokemon.pokemonTypes.domain.interfaces.PokemonTypeParserInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named("JSON")
public class JsonPokemonTypeParser implements PokemonTypeParserInterface {

    @Override
    public List<PokemonType> toPokemonTypeList(Object origin) {
        JSONObject originJson = (JSONObject) origin;

        List<PokemonType> pokemonTypes = new ArrayList<>();

        JSONArray typesArray = (JSONArray) originJson.get("types");

        for (int i = 0; i < typesArray.size(); i++) {
            JSONObject parentTypeItemObject = (JSONObject)typesArray.get(i);
            JSONObject childTypeItemObject = (JSONObject)parentTypeItemObject.get("type");

            PokemonType pokemonType = PokemonType.create(
                    childTypeItemObject.get("name").toString(),
                    Integer.parseInt(originJson.get("id").toString())
            );
            pokemonTypes.add(pokemonType);
        }

        return pokemonTypes;
    }
}

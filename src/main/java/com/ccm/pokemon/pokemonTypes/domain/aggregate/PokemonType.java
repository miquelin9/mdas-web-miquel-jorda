package com.ccm.pokemon.pokemonTypes.domain.aggregate;

import com.ccm.pokemon.pokemonTypes.domain.valueObjects.Name;
import com.ccm.pokemon.pokemonTypes.domain.valueObjects.PokemonId;
import org.json.simple.JSONObject;

public class PokemonType {

    private PokemonType (Name name, PokemonId pokemonId) {
        this.name = name;
        this.pokemonId = pokemonId;
    }

    public static PokemonType create (String name, int pokemonId) {
        return new PokemonType(new Name(name), new PokemonId(pokemonId));
    }

    private Name name;
    private PokemonId pokemonId;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("id", this.pokemonId);
        return jsonObject.toJSONString();
    }


}

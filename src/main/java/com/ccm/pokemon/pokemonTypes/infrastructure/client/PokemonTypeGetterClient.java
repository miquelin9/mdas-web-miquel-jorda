package com.ccm.pokemon.pokemonTypes.infrastructure.client;

import com.ccm.pokemon.pokemonTypes.domain.aggregate.PokemonType;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.domain.interfaces.PokemonTypeGetterInterface;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named("Http")
public class PokemonTypeGetterClient implements PokemonTypeGetterInterface {

    private static final String HOST_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/";

    @Override
    public JSONObject getPokemonTypeJsonByPokemonName(String pokemonName) throws PokemonNotFoundException {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(HOST_ENDPOINT + pokemonName);

            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            JSONParser parser = new JSONParser();
            String str = EntityUtils.toString(entity);

            try {
                return (JSONObject) parser.parse(str);
            } catch (Exception e) {
                throw new PokemonNotFoundException("Pokemon '" + pokemonName + "' not found");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}


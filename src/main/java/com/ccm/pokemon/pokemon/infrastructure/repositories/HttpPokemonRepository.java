package com.ccm.pokemon.pokemon.infrastructure.repositories;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.infrastructure.parsers.JsonToPokemonParser;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

@ApplicationScoped
@Named("HttpPokemon")
public class HttpPokemonRepository implements PokemonRepository {

    private static final String HOST_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/";
    private static final double TIMEOUT = 3;

    @Inject
    JsonToPokemonParser jsonToPokemonParser;

    @Override
    public Pokemon find(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, NetworkConnectionException, UnknownException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(HOST_ENDPOINT + pokemonId.getPokemonId());
        RequestConfig.Builder requestConfig = RequestConfig.custom();
        requestConfig.setConnectTimeout((int) (TIMEOUT * 1000.0));
        requestConfig.setConnectionRequestTimeout((int) (TIMEOUT * 1000.0));
        requestConfig.setSocketTimeout((int) (TIMEOUT * 1000.0));
        request.setConfig(requestConfig.build());

        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            JSONParser parser = new JSONParser();
            String str = EntityUtils.toString(entity);
            if (str.equals("Not Found")) {
                throw new PokemonNotFoundException("Pokemon '" + pokemonId.getPokemonId() + "' not found");
            }

            JSONObject pokemonResponse = (JSONObject) parser.parse(str);
            return jsonToPokemonParser.castJsonToPokemon(pokemonResponse);
        } catch (ConnectTimeoutException | SocketTimeoutException e) {
            throw new TimeoutException("Timeout period expired. Response took too long to arrive");
        } catch (UnknownHostException e) {
            throw new NetworkConnectionException("Unable to reach specified host (maybe the network is down)");
        } catch (PokemonNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnknownException("There was some unknown problem while processing the request to the external API");
        }
    }
}

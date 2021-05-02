package com.ccm.pokemon.pokemon.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PokemonControllerTest {
    @Test
    public void shouldGetPokemon() {
        Response r = given()
            .pathParam("id", 1)
            .get("/pokemon/get/{id}")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response();

        assertEquals((Integer) r.jsonPath().get("id"), 1);
        assertEquals(r.jsonPath().get("name"), "bulbasaur");
    }

    @Test
    public void shouldNotGetPokemon() {
        given()
            .pathParam("id", 0)
            .get("/pokemon/get/{id}")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NOT_FOUND);

    }
}

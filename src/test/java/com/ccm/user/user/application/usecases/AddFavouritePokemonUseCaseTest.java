package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserFavouritePokemonDTO;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.services.AddFavouritePokemonToUser;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@QuarkusTest
public class AddFavouritePokemonUseCaseTest {
    @Inject
    AddFavouritePokemonUseCase tested;

    @Test
    public void verify_addFavouritePokemon_CallsToMethods() throws UserNotFoundException, FavouritePokemonAlreadyExistsException, IOException {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
            pokemonId.getPokemonId(),
            userId.getUserId()
        );

        AddFavouritePokemonToUser addFavouritePokemonToUser = Mockito.mock(AddFavouritePokemonToUser.class);
        Mockito.doNothing().when(addFavouritePokemonToUser).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemonToUser, AddFavouritePokemonToUser.class);

        tested.addFavouritePokemon(userFavouritePokemonDTO);

        Mockito.verify(addFavouritePokemonToUser, times(1)).execute(pokemonId, userId);
    }

    @Test
    public void verify_addFavouritePokemon_throwsUserNotFoundException () throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
                pokemonId.getPokemonId(),
                userId.getUserId()
        );

        AddFavouritePokemonToUser addFavouritePokemonToUser = Mockito.mock(AddFavouritePokemonToUser.class);
        Mockito.doThrow(UserNotFoundException.class).when(addFavouritePokemonToUser).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemonToUser, AddFavouritePokemonToUser.class);

        assertThrows(UserNotFoundException.class, () -> {
            tested.addFavouritePokemon(userFavouritePokemonDTO);
        });
    }

    @Test
    public void verify_addFavouritePokemon_throwsFavouritePokemonAlreadyExistsException () throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
                pokemonId.getPokemonId(),
                userId.getUserId()
        );

        AddFavouritePokemonToUser addFavouritePokemonToUser = Mockito.mock(AddFavouritePokemonToUser.class);
        Mockito.doThrow(FavouritePokemonAlreadyExistsException.class).when(addFavouritePokemonToUser).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemonToUser, AddFavouritePokemonToUser.class);

        assertThrows(FavouritePokemonAlreadyExistsException.class, () -> {
            tested.addFavouritePokemon(userFavouritePokemonDTO);
        });
    }
}

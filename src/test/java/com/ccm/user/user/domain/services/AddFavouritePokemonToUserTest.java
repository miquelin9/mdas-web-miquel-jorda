package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.entities.FavouritePokemon;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
public class AddFavouritePokemonToUserTest {
    @Inject
    AddFavouritePokemonToUser tested;

    @Test
    public void verify_execute_callsToMethods() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);
        FavouritePokemonId favouritePokemonId = new FavouritePokemonId(123);

        UserFinder userFinder = Mockito.mock(UserFinder.class);
        UserSaver userSaver = Mockito.mock(UserSaver.class);
        when(userFinder.findUser(userId)).thenReturn(user);
        when(userSaver.saveUser(user)).thenReturn(user);
        QuarkusMock.installMockForType(userFinder, UserFinder.class);
        QuarkusMock.installMockForType(userSaver, UserSaver.class);

        tested.execute(favouritePokemonId, userId);
        Mockito.verify(userFinder, Mockito.times(1)).findUser(userId);
        Mockito.verify(userSaver, Mockito.times(1)).saveUser(user);
    }

    @Test
    public void verify_execute_throwsUserNotFoundException() throws UserNotFoundException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);
        FavouritePokemonId favouritePokemonId = new FavouritePokemonId(123);

        UserFinder userFinder = Mockito.mock(UserFinder.class);
        UserSaver userSaver = Mockito.mock(UserSaver.class);
        when(userFinder.findUser(userId)).thenThrow(UserNotFoundException.class);
        when(userSaver.saveUser(user)).thenReturn(user);
        QuarkusMock.installMockForType(userFinder, UserFinder.class);
        QuarkusMock.installMockForType(userSaver, UserSaver.class);

        assertThrows(UserNotFoundException.class, () -> {
            tested.execute(favouritePokemonId, userId);
        });
    }

    @Test
    public void verify_execute_throwsFavouritePokemonAlreadyExistsException() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        UserId userId = new UserId(1);
        User user = Mockito.mock(User.class);
        FavouritePokemonId favouritePokemonId = new FavouritePokemonId(123);
        FavouritePokemon favouritePokemon = new FavouritePokemon(favouritePokemonId);

        UserFinder userFinder = Mockito.mock(UserFinder.class);
        UserSaver userSaver = Mockito.mock(UserSaver.class);
        doThrow(FavouritePokemonAlreadyExistsException.class).when(user).addFavouritePokemon(favouritePokemon);
        when(userFinder.findUser(userId)).thenReturn(user);
        when(userSaver.saveUser(user)).thenReturn(user);
        QuarkusMock.installMockForType(userFinder, UserFinder.class);
        QuarkusMock.installMockForType(userSaver, UserSaver.class);

        assertThrows(FavouritePokemonAlreadyExistsException.class, () -> {
            tested.execute(favouritePokemonId, userId);
        });
    }
}

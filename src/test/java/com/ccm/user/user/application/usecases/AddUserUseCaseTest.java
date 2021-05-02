package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserDTO;
import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.services.UserCreator;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
public class AddUserUseCaseTest {
    @Inject
    AddUserUseCase tested;

    @Test
    public void verify_createUser_CallsToMethods() throws UserAlreadyExistsException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);
        UserDTO userDTO = new UserDTO("keko", 1);

        UserCreator userCreator = Mockito.mock(UserCreator.class);
        Mockito.doNothing().when(userCreator).createUser(user);
        QuarkusMock.installMockForType(userCreator, UserCreator.class);

        tested.createUser(userDTO);

        verify(userCreator, times(1)).createUser(any());
    }

    @Test
    public void verify_createUser_throwsUserAlreadyExistsException () throws UserAlreadyExistsException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        UserDTO userDTO = new UserDTO("keko", 1);

        UserCreator userCreator = Mockito.mock(UserCreator.class);
        Mockito.doThrow(UserAlreadyExistsException.class).when(userCreator).createUser(any());
        QuarkusMock.installMockForType(userCreator, UserCreator.class);

        assertThrows(UserAlreadyExistsException.class, () -> {
            tested.createUser(userDTO);
        });
    }
}

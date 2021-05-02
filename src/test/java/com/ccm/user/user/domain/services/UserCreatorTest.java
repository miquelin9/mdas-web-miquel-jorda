package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.interfaces.UserRepository;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import com.ccm.user.user.infrastructure.InMemoryUserRepository;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
public class UserCreatorTest {
    @Inject
    UserCreator tested;

    @Test
    public void verify_createUser_callsToMethods() throws UserAlreadyExistsException {
        User user = mock(User.class);

        UserRepository userRepository = mock(InMemoryUserRepository.class);
        Mockito.doNothing().when(userRepository).create(user);
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        tested.createUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).create(Mockito.any());
    }

    @Test()
    public void verify_createUser_throwsUserAlreadyExistsException_whenUserIsAlreadyCreated() {
        User user = new User(
            new UserName("keko"),
            new UserId(1)
        );

        UserRepository userRepository = mock(InMemoryUserRepository.class);
        doReturn(true).when(userRepository).exists(user.getUserId());
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        assertThrows(UserAlreadyExistsException.class, () -> {tested.createUser(user);});
    }
}

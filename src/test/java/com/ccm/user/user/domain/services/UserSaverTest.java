package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
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
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
public class UserSaverTest {
    @Inject
    UserSaver tested;

    @Test
    public void verify_saveUser_callsToMethods() throws UserNotFoundException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);

        UserRepository userRepository = Mockito.mock(InMemoryUserRepository.class);
        when(userRepository.update(user)).thenReturn(user);
        doReturn(true).when(userRepository).exists(user.getUserId());
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        tested.saveUser(user);
        verify(userRepository, Mockito.times(1)).update(user);
    }

    @Test
    public void verify_saveUser_throwsUserNotFoundException() {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);

        UserRepository userRepository = Mockito.mock(InMemoryUserRepository.class);
        doReturn(false).when(userRepository).exists(user.getUserId());
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        assertThrows(UserNotFoundException.class, () -> {
            tested.saveUser(user);
        });
    }
}

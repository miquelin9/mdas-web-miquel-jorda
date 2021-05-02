package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
public class UserFinderTest {
    @Inject
    UserFinder tested;

    @Test
    public void verify_findUser_callsToMethods() throws UserNotFoundException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);

        UserRepository userRepository = mock(InMemoryUserRepository.class);
        Mockito.when(userRepository.find(userId)).thenReturn(user);
        Mockito.when(userRepository.exists(userId)).thenReturn(true);
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        tested.findUser(userId);

        verify(userRepository, times(1)).find(any());
    }

    @Test
    public void verify_createUser_throwsUserNotFoundException() {
        UserId userId = new UserId(1);

        UserRepository userRepository = mock(InMemoryUserRepository.class);
        doReturn(false).when(userRepository).exists(userId);
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        assertThrows(UserNotFoundException.class, () -> {tested.findUser(userId);});
    }
}

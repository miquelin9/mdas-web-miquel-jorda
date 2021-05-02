package com.ccm.user.user.infrastructure;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.interfaces.UserRepository;
import com.ccm.user.user.domain.services.UserMother;
import com.ccm.user.user.domain.services.UsersMother;
import com.ccm.user.user.domain.vo.UserName;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class InMemoryUserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void shouldFindAUserById() {
        UserRepository userRepository = new InMemoryUserRepository();

        //Given
        User user = UserMother.random();

        //When
        userRepository.create(user);

        //Then
        Assertions.assertEquals(user, userRepository.find(user.getUserId()));
    }

    @Test
    public void shouldCheckIfAUserExistsById() {
        //Given
        User user = UserMother.random();

        //When
        userRepository.deleteAll();
        userRepository.create(user);

        //Then
        assertTrue(userRepository.exists(user.getUserId()));
    }

    @Test
    public void shouldSearchUsersByName() {
        //Given
        List<User> users = UsersMother.random();

        userRepository.deleteAll();
        //When
        users.forEach(user -> {
            userRepository.create(user);
        });

        //Then
        List<User> retrievedUsers = new ArrayList<>();
        users.forEach(user -> {
            retrievedUsers.addAll(userRepository.search(user.getName()));
        });
        assertEquals(users, retrievedUsers);
    }

    @Test
    public void shouldUpdateUser() {
        //Given
        User user = UserMother.random();

        //When
        userRepository.deleteAll();
        userRepository.create(user);
        User sameUserDifferentName = UserMother.randomWithCustomId(user.getUserId());
        userRepository.update(sameUserDifferentName);

        //Then
        assertEquals(sameUserDifferentName, userRepository.find(sameUserDifferentName.getUserId()));
    }

    @Test
    public void shouldCreateUser() {
        //Given
        User user = UserMother.random();

        //When
        userRepository.deleteAll();
        userRepository.create(user);

        //Then
        assertTrue(userRepository.exists(user.getUserId()));
        assertEquals(userRepository.find(user.getUserId()), user);
    }

    @Test
    public void shouldDeleteUser() {
        //Given
        User user = UserMother.random();

        //When
        userRepository.deleteAll();
        userRepository.create(user);

        //Then
        assertTrue(userRepository.exists(user.getUserId()));
        userRepository.delete(user);
        assertFalse(userRepository.exists(user.getUserId()));
    }
}

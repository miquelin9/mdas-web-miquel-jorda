package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.interfaces.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class UserSaver {

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    public User saveUser(User user) throws UserNotFoundException {
        guard(user);

        return userRepository.update(user);
    }

    private void guard (User user) throws UserNotFoundException {
        if (!userRepository.exists(user.getUserId())) {
            throw new UserNotFoundException("User " + user.getName().getName() + " doesn't exist");
        }
    }
}

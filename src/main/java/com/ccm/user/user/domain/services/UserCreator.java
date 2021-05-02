package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.interfaces.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class UserCreator {

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    public void createUser(User user) throws UserAlreadyExistsException {
        guard(user);

        userRepository.create(user);
    }

    private void guard (User user) throws UserAlreadyExistsException {
        if (userRepository.exists(user.getUserId())) {
            throw new UserAlreadyExistsException("User " + user.getName().getName() + " already exists");
        }
    }
}

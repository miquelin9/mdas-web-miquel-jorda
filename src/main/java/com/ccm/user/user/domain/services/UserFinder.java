package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.interfaces.UserRepository;
import com.ccm.user.user.domain.vo.UserId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class UserFinder {

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    public User findUser(UserId userId) throws UserNotFoundException {
        guard(userId);
        return userRepository.find(userId);
    }

    private void guard (UserId userId) throws UserNotFoundException{
        if (!userRepository.exists(userId)) {
            throw new UserNotFoundException("User " + userId.toString() + " not found");
        }
    }
}

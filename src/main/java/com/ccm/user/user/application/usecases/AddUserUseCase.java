package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserDTO;
import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.services.UserCreator;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddUserUseCase {
    @Inject
    UserCreator userCreator;

    public void createUser (UserDTO user) throws UserAlreadyExistsException {
        userCreator.createUser(
            new User(
                new UserName(user.getName()),
                new UserId(user.getUserId())
            )
        );
    }
}

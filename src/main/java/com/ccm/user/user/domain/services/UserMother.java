package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.vo.UserId;

public class UserMother {
    public static User random() {
        return new User(
            UserNameMother.random(),
            UserIdMother.random()
        );
    }

    public static User randomWithCustomId(UserId userId) {
        return new User(
                UserNameMother.random(),
                userId
        );
    }
}

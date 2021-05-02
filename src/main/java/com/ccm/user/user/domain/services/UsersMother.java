package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;

import java.util.ArrayList;
import java.util.List;

public class UsersMother {
    private static final int NUM_USERS = 10;

    public static List<User> random() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < NUM_USERS; i++) {
            users.add(UserMother.random());
        }
        return users;
    }
}

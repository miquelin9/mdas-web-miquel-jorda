package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.vo.UserName;
import com.github.javafaker.Faker;

public class UserNameMother {
    public static UserName random() {
        return new UserName(new Faker().name().name());
    }
}

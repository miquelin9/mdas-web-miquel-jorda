package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.vo.UserId;
import com.github.javafaker.Faker;

public class UserIdMother {
    public static UserId random() {
        return new UserId((int) new Faker().number().randomNumber(2, true));
    }
}

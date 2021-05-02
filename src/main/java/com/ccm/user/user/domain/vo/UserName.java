package com.ccm.user.user.domain.vo;

import java.util.Objects;

public class UserName {

    public UserName(String name) {
        this.name = name;
    }

    public boolean contains(UserName userName){
        return userName.getName().contains(this.name);
    }

    public String getName() {
        return name;
    }

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(name, userName.name);
    }

}

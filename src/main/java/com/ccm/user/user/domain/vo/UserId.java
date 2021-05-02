package com.ccm.user.user.domain.vo;

public class UserId {

    public UserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return String.valueOf(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId1 = (UserId) o;
        return userId == userId1.userId;
    }
}

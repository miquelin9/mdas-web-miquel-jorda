package com.ccm.user.user.application.dto;

public class UserDTO {
    private String name;
    private int userId;

    public UserDTO(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public int getUserId() {
        return userId;
    }
}

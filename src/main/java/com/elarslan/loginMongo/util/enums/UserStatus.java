package com.elarslan.loginMongo.util.enums;


public enum UserStatus {

    PASSIVE(0), ACTIVE(1);

    private int userStatusCode;

    UserStatus(int userStatusCode) {
        this.userStatusCode = userStatusCode;
    }

    public int getUserStatusCode() {
        return userStatusCode;
    }
}

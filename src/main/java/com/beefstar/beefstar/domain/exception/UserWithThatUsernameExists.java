package com.beefstar.beefstar.domain.exception;

public class UserWithThatUsernameExists  extends RuntimeException {
    public UserWithThatUsernameExists(String message) {
        super(message);
    }


}
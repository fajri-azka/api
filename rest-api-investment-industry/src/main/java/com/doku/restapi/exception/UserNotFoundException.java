package com.doku.restapi.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){ super("Can't find the user id COK");}
    public UserNotFoundException(String message){ super(message);}
}

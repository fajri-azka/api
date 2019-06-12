package com.doku.restapi.exception;

public class UserIsEmpyException extends RuntimeException {

    public UserIsEmpyException (){ super("USER E KOSONG MAS BROOOOO");}
    public UserIsEmpyException (String message){ super(message);}
}

package com.doku.restapi.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(){ super("Can't find the data");}
    public DataNotFoundException(String message){ super(message);}
}

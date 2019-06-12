package com.doku.restapi.exception;

public class FieldMustNotBeEmptyException extends RuntimeException{

    public FieldMustNotBeEmptyException(){ super("Field gak boleh kosong coyyy awokaowkoakwok");}
    public FieldMustNotBeEmptyException(String message){ super(message);}
}

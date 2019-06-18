package com.doku.restapi.model;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter @Getter @ToString
public class UserRequestResponse {
    //@Size(min = 1, message = "must not empty")
    private int userId;
    @Size(min = 2, message = "must not be less than 2 characters")
    private String fullName;
    @Size(min = 2, message = "must not be less than 2 characters")
    private String userAddress;
    @NumberFormat
    @NotEmpty(message = "must be numbers")
    private int stockRequest;
   // @Size(message = "must be numbers")
    private int currentMoney;
}
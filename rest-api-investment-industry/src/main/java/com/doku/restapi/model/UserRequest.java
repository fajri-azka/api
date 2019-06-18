package com.doku.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter @NotNull
public class UserRequest {
    //@Size(min = 1, message = "must not be empty")
    private int userId;
    @Size(min = 2, message = "must not be less than 2 characters")
    private String fullName;
    @Size(min = 2, message = "must not be less than 2 characters")
    private String userAddress;
    //@Size(min, message = "must be numbers")
    private int stockRequest;
    //@Size(message = "must not be less than 8 numbers")
    private int currentMoney;
}

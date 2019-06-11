package com.doku.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter @NotNull
public class UserRequest {
    private String userId;
    private String fullName;
    private String userAddress;
    private int ktpIdNumber;
    private int currentMoney;
}

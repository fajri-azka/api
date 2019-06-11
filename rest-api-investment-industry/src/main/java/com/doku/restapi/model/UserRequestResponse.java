package com.doku.restapi.model;

import lombok.*;

@Setter @Getter @ToString
public class UserRequestResponse {
    private String userId;
    private String fullName;
    private String userAddress;
    private int ktpIdNumber;
    private int currentMoney;
}
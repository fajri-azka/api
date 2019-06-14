package com.doku.restapi.model;

import lombok.*;

@Setter @Getter @ToString
public class DataSahamRequestResponse {
    private String userId;
    private String fullName;
    private String stockId;
    private String stockName;
    private int stockSheetRequest;    // get from userRequestResponse
    private int stockPrice;           // stockPrice
    private int stockPriceTotal;      // stockPrice*stockSheet
}

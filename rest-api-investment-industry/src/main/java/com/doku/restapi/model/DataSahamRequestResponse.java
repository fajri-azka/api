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
    private int moneyBalance;       // currentMoney-stockPriceTotal
    private double returnDaily;     // stockPriceTotal*dailyReturn
    private double returnMonthly;   // returnDaily*30
    private double returnYearly;    // returnMonthly*12

}

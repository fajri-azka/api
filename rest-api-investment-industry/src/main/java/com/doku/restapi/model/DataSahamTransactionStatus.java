package com.doku.restapi.model;

import lombok.*;

@Setter
@Getter

public class DataSahamTransactionStatus {
    private String userId;
    private String messageTransactionStatus;// Transactino Status
    private int moneyBalance;               // currentMoney-stockPriceTotal
    private double returnDaily;             // stockPriceTotal*dailyReturn
    private double returnMonthly;           // returnDaily*30
    private double returnYearly;            // returnMonthly*12
}

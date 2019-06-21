package com.doku.restapi.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataSahamTransactionList {
    private int transactionNumber;
    private String messageTransactionStatus;// Transaction Status
    private String transactionCode;
    private String userId;
    private String userName;
    private String stockId;
    private String stockName;
    private int stockRequest;
    private int moneyBalance;               // currentMoney-stockPriceTotal
    private double returnDaily;             // stockPriceTotal*dailyReturn
    private double returnMonthly;           // returnDaily*30
    private double returnYearly;            // returnMonthly*12
}

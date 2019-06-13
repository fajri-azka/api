package com.doku.restapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DataSahamRequest {
    private String stockId = "1A-777-UAE649";
    private String stockName = "BANK-BLACK-BEAR";
    private int stockPrice = 1250 ;
    private double stockDailyReturn = 0.015 ; //dalam %
}

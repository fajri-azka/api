package com.doku.restapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DataSahamRequest {
    private String stockId;
    private String stockName;
    private int stockPrice;
    private double stockDailyReturn; //dalam %
}

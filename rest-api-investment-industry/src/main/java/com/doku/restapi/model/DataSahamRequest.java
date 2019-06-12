package com.doku.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter @NotNull
public class DataSahamRequest {
    private String sahamId = "1";
    private String sahamName = "BANK-BLACK-BEAR";
    private int hargaPerLembar = 100000 ;
    private double returnHarian = 0.015 ; //dalam %
}

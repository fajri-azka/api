package com.doku.restapi.services;

import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;
import com.doku.restapi.services.implement.UserServicesImplement;

import java.util.Collection;

public interface TransactionServices {
    //Collection<DataSahamRequest> getStockDetail();
    DataSahamRequestResponse createTransaction(String transactionUserId, DataSahamRequest dataSahamRequest);
    DataSahamRequestResponse updateTransaction(String transactionUserId, DataSahamRequest dataSahamRequest);
//    serServicesImplement getUser();
}

package com.doku.restapi.services;

import com.doku.restapi.model.DataSaham;
import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;

public interface TransactionServices {
    DataSaham getStock(DataSahamRequest dataSahamRequest);
    DataSahamRequestResponse createTransaction(String transactionUserId, DataSahamRequest dataSahamRequest);
    DataSahamRequestResponse updateTransaction(String transactionUserId, DataSahamRequest dataSahamRequest);
//    serServicesImplement getUser();
}

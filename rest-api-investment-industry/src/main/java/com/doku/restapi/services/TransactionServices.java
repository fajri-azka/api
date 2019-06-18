package com.doku.restapi.services;

import com.doku.restapi.model.DataSaham;
import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;
import com.doku.restapi.model.DataSahamTransactionStatus;

import java.util.Collection;

public interface TransactionServices {
    DataSahamRequest createStock(DataSaham dataSaham);
    DataSahamRequest getStock(String stockId);
    Collection <DataSahamRequest> getAllStock();
    DataSahamRequestResponse createTransaction(DataSahamRequestResponse dataSahamRequestResponse);
    DataSahamTransactionStatus updateTransaction(DataSahamRequestResponse dataSahamRequestResponse);
    Collection <DataSahamTransactionStatus> getAllTransaction();
}

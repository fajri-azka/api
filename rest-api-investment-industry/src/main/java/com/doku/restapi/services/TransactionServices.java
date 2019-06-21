package com.doku.restapi.services;

import com.doku.restapi.model.*;

import java.util.Collection;

public interface TransactionServices {
    DataSahamRequest createStock(DataSaham dataSaham);
    DataSahamRequest getStock(String stockId);
    Collection <DataSahamRequest> getAllStock();
    DataSahamRequestResponse createTransaction(DataSahamRequestResponse dataSahamRequestResponse);
    DataSahamTransactionStatus updateTransaction(DataSahamRequestResponse dataSahamRequestResponse);
    DataSahamTransactionList getTransaction (String transactionNumber);
    Collection <DataSahamTransactionList> getAllTransaction();

}

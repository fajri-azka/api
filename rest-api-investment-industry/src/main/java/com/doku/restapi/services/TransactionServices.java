package com.doku.restapi.services;

import com.doku.restapi.model.DataSaham;
import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;
import com.doku.restapi.model.DataSahamTransactionStatus;

public interface TransactionServices {
    DataSahamRequest getStock(DataSaham dataSaham);
    DataSahamRequestResponse createTransaction(String transactionUserId, DataSaham dataSaham);
    DataSahamTransactionStatus updateTransaction(String transactionUserId, DataSahamRequest dataSahamRequest);
}

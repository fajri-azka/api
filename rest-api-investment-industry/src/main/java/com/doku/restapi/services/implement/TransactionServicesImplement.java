package com.doku.restapi.services.implement;

import com.doku.restapi.model.*;
import com.doku.restapi.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service(value = "TransactionServices")
public class TransactionServicesImplement implements TransactionServices {

    @Autowired
    private UserServicesImplement userServicesImplement;

    DataSahamRequestResponse returnSaham;
    HashMap<String, DataSahamRequestResponse> saham;

    DataSahamRequest returnSahamRequest;
    HashMap<String, DataSahamRequest> sahamrequest;

    DataSahamTransactionStatus returnTransaction;
    HashMap<String, DataSahamTransactionStatus> transactionstatus;

    public DataSahamRequest getStock(DataSaham dataSaham) {
        returnSahamRequest = new DataSahamRequest();

        returnSahamRequest.setStockId(dataSaham.getStockId());
        String stockid = returnSahamRequest.getStockId();

        returnSahamRequest.setStockName(dataSaham.getStockName());
        returnSahamRequest.setStockPrice(dataSaham.getStockPrice());
        returnSahamRequest.setStockDailyReturn(dataSaham.getStockDailyReturn());

        if (sahamrequest == null){
            sahamrequest = new HashMap<>();
        }

        sahamrequest.put(stockid, returnSahamRequest);
        return returnSahamRequest;
    }

    //@Override
    public  DataSahamRequestResponse createTransaction(String userid, DataSaham dataSaham) {
        returnSaham = new DataSahamRequestResponse();

        returnSaham.setUserId(userid);
        String usertransactionid = returnSaham.getUserId();

        UserRequestResponse userRequestResponse = userServicesImplement.getUser(userid);

        returnSaham.setFullName(userRequestResponse.getFullName());
        returnSaham.setStockId(dataSaham.getStockId());
        returnSaham.setStockName(dataSaham.getStockName());
        returnSaham.setStockSheetRequest(userRequestResponse.getStockRequest());
        returnSaham.setStockPrice(dataSaham.getStockPrice());
        returnSaham.setStockPriceTotal(dataSaham.getStockPrice()*userRequestResponse.getStockRequest());

        if (saham == null){
            saham = new HashMap<>();
        }

        saham.put(usertransactionid, returnSaham);
        return returnSaham;

    }

    public DataSahamTransactionStatus updateTransaction(String userid, DataSahamRequest dataSahamRequest) {
        returnTransaction = new DataSahamTransactionStatus();

        returnTransaction.setUserId(userid);
        String usertransactionid = returnTransaction.getUserId();

        UserRequestResponse userRequestResponse = userServicesImplement.getUser(userid);

        returnTransaction.setMessageTransactionStatus("Transaction Success");
        returnTransaction.setMoneyBalance(userRequestResponse.getCurrentMoney() - returnSaham.getStockPriceTotal());
        returnTransaction.setReturnDaily(returnSaham.getStockPriceTotal()*returnSahamRequest.getStockDailyReturn());
        returnTransaction.setReturnMonthly(returnTransaction.getReturnDaily()*30);
        returnTransaction.setReturnYearly(returnTransaction.getReturnMonthly()*12);


        if ( transactionstatus == null) {
            transactionstatus = new HashMap<>();
        }

        transactionstatus.put(usertransactionid, returnTransaction);
        return returnTransaction;
    }
}


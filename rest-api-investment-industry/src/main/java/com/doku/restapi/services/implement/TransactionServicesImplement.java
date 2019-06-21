package com.doku.restapi.services.implement;

import com.doku.restapi.exception.DataNotFoundException;
import com.doku.restapi.model.*;
import com.doku.restapi.services.TransactionServices;
import com.doku.restapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service(value = "TransactionServices")
public class TransactionServicesImplement implements TransactionServices {

    @Autowired
    private UserServices userServices;

    @Autowired
    private TransactionServices transactionServices;

    DataSahamRequestResponse returnSahamResponse;
    HashMap<String, DataSahamRequestResponse> sahamResponse;

    DataSahamRequest returnSahamRequest;
    HashMap<String, DataSahamRequest> sahamRequest;

    DataSahamTransactionStatus returnTransaction;
    HashMap<String, DataSahamTransactionStatus> transactionstatus;

    DataSahamTransactionList returnTransactionList;
    HashMap<String, DataSahamTransactionList> transactionlist;

    int transactionNumber=0;

    public DataSahamRequest createStock(DataSaham dataSaham) {
        returnSahamRequest = new DataSahamRequest();

        returnSahamRequest.setStockId(dataSaham.getStockId());
        String stockId = returnSahamRequest.getStockId();

        returnSahamRequest.setStockName(dataSaham.getStockName());
        returnSahamRequest.setStockPrice(dataSaham.getStockPrice());
        returnSahamRequest.setStockDailyReturn(dataSaham.getStockDailyReturn());

        if (sahamRequest == null){
            sahamRequest = new HashMap<>();
        }

        sahamRequest.put(stockId, returnSahamRequest);
        return returnSahamRequest;
    }

    public Collection <DataSahamRequest> getAllStock() {
        return sahamRequest.values();
    }

    public DataSahamRequest getStock (String stockId){ return sahamRequest.get(stockId); }

    @Override
    public  DataSahamRequestResponse createTransaction(DataSahamRequestResponse dataSahamRequestResponse) {
        returnSahamResponse = new DataSahamRequestResponse();

        //returnSahamResponse.setUserId(userId);
        String userTransactonId = dataSahamRequestResponse.getUserId();
        String stockTransactonId = dataSahamRequestResponse.getStockId();


        UserRequestResponse userRequestResponse = userServices.getUser(userTransactonId);
        DataSahamRequest dataSahamRequest = transactionServices.getStock(stockTransactonId);

        int stockUserRequestTemp = userRequestResponse.getStockRequest();
        int stockPriceTemp = dataSahamRequest.getStockPrice();

        returnSahamResponse.setUserId(userTransactonId);
        returnSahamResponse.setFullName(userRequestResponse.getFullName());
        returnSahamResponse.setStockId(dataSahamRequest.getStockId());
        returnSahamResponse.setStockName(dataSahamRequest.getStockName());
        returnSahamResponse.setStockSheetRequest(userRequestResponse.getStockRequest());
        returnSahamResponse.setStockPrice(dataSahamRequest.getStockPrice());
        returnSahamResponse.setStockPriceTotal(stockPriceTemp * stockUserRequestTemp);

        if (sahamResponse == null){
            sahamResponse = new HashMap<>();
        }

        sahamResponse.put(userTransactonId, returnSahamResponse);
        return returnSahamResponse;

    }


    public DataSahamTransactionStatus updateTransaction(DataSahamRequestResponse dataSahamRequestResponse) {
        returnTransaction = new DataSahamTransactionStatus();
        returnTransactionList = new DataSahamTransactionList();
        String userID = returnSahamResponse.getUserId();

        returnTransaction.setUserId(userID);
        String usertransactionid = returnTransaction.getUserId();

        UserRequestResponse userRequestResponse = userServices.getUser(userID);
        String stockTransactionId = dataSahamRequestResponse.getStockId();

        DataSahamRequest dataSahamRequestTemp = transactionServices.getStock(stockTransactionId);

        returnTransactionList.setUserId(returnTransaction.getUserId());
        returnTransactionList.setUserName(userRequestResponse.getFullName());
        returnTransactionList.setStockId(returnSahamResponse.getStockId());
        returnTransactionList.setStockName(returnSahamResponse.getStockName());
        returnTransactionList.setStockRequest(returnSahamResponse.getStockSheetRequest());

        int currentMoneyTemp = userRequestResponse.getCurrentMoney();           //Current Money Temporary
        int stockPriceTotal = returnSahamResponse.getStockPriceTotal();         //Stock Price Total
        double stockDailyReturn = returnSahamRequest.getStockDailyReturn();     //Stock Daily Return

        if (currentMoneyTemp >= stockPriceTotal) {

            returnTransaction.setMessageTransactionStatus("Transaction Success");
            returnTransaction.setMoneyBalance(currentMoneyTemp - stockPriceTotal);
            returnTransaction.setReturnDaily(stockPriceTotal * stockDailyReturn);
            returnTransaction.setReturnMonthly(returnTransaction.getReturnDaily() * 30);
            returnTransaction.setReturnYearly(returnTransaction.getReturnMonthly() * 12);

            userRequestResponse.setCurrentMoney(returnTransaction.getMoneyBalance());

            returnTransactionList.setTransactionCode(UUID.randomUUID().toString().replace("-", ""));

            returnTransactionList.setMoneyBalance(userRequestResponse.getCurrentMoney());
            returnTransactionList.setMessageTransactionStatus(returnTransaction.getMessageTransactionStatus());
            returnTransactionList.setReturnDaily(returnTransaction.getReturnDaily());
            returnTransactionList.setReturnMonthly(returnTransaction.getReturnMonthly());
            returnTransactionList.setReturnYearly(returnTransaction.getReturnYearly());


            if (transactionstatus == null) {
                transactionstatus = new HashMap<>();
                transactionlist = new HashMap<>();

                transactionNumber = 1;

                returnTransactionList.setTransactionNumber(transactionNumber);

                String TrxNum = Integer.toString(transactionNumber);
                transactionlist.put(TrxNum, returnTransactionList);

            } else {
                transactionNumber += 1;
                returnTransactionList.setTransactionNumber(transactionNumber);

                String TrxNum = Integer.toString(transactionNumber);

                transactionlist.put(TrxNum, returnTransactionList);
                transactionstatus.put(usertransactionid, returnTransaction);

            }
            //transactionstatus.put(usertransactionid, returnTransaction);

            return returnTransaction;

        } else {
            throw new DataNotFoundException("YOUR BALANCE IS NOT ENOUGH");
        }
    }

    public DataSahamTransactionList getTransaction (String transactionNumber){ return transactionlist.get(transactionNumber); }

    public Collection <DataSahamTransactionList> getAllTransaction() {
        return transactionlist.values();
    }

}


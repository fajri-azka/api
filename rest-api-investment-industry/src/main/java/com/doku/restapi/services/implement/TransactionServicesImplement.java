package com.doku.restapi.services.implement;

import com.doku.restapi.model.DataSaham;
import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;
import com.doku.restapi.model.UserRequestResponse;
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

    DataSaham dataSaham;
    HashMap<String, DataSaham> sahamreq;

    public DataSaham getStock(DataSahamRequest dataSahamRequest) {
        dataSaham = new DataSaham();

        dataSaham.setStockId(dataSahamRequest.getStockId());
        String stockid = dataSaham.getStockId();

        dataSaham.setStockName(dataSahamRequest.getStockName());
        dataSaham.setStockPrice(dataSahamRequest.getStockPrice());
        dataSaham.setStockDailyReturn(dataSahamRequest.getStockDailyReturn());

        if (sahamreq == null){
            sahamreq = new HashMap<>();
        }

        sahamreq.put(stockid, dataSaham);
        return dataSaham;
    }


    @Override
    public  DataSahamRequestResponse createTransaction(String userid, DataSahamRequest dataSahamRequest) {
        returnSaham = new DataSahamRequestResponse();

        returnSaham.setUserId(userid);
        String usertransactionid = returnSaham.getUserId();

        UserRequestResponse userRequestResponse = userServicesImplement.getUser(userid);

        returnSaham.setFullName(userRequestResponse.getFullName());
        returnSaham.setStockId(dataSahamRequest.getStockId());
        returnSaham.setStockName(dataSahamRequest.getStockName());
        returnSaham.setStockSheetRequest(userRequestResponse.getStockRequest());
        returnSaham.setStockPrice(dataSahamRequest.getStockPrice());
        returnSaham.setStockPriceTotal(dataSahamRequest.getStockPrice()*userRequestResponse.getStockRequest());
        returnSaham.setMoneyBalance(userRequestResponse.getCurrentMoney() - returnSaham.getStockPriceTotal());
        returnSaham.setReturnDaily(returnSaham.getStockPriceTotal()*dataSahamRequest.getStockDailyReturn());
        returnSaham.setReturnMonthly(returnSaham.getReturnDaily()*30);
        returnSaham.setReturnYearly(returnSaham.getReturnMonthly()*12);

        if (saham == null){
            saham = new HashMap<>();
        }

        saham.put(usertransactionid, returnSaham);
        return returnSaham;

    }

    public  DataSahamRequestResponse updateTransaction(String userid, DataSahamRequest dataSahamRequest) {

        returnSaham.setUserId(userid);
        String usertransactionid = returnSaham.getUserId();

        UserRequestResponse userRequestResponse = userServicesImplement.getUser(userid);

        returnSaham.setStockPriceTotal(dataSahamRequest.getStockPrice()*userRequestResponse.getStockRequest());
        returnSaham.setMoneyBalance(userRequestResponse.getCurrentMoney() - returnSaham.getStockPriceTotal());
        returnSaham.setReturnDaily(returnSaham.getStockPriceTotal()*dataSahamRequest.getStockDailyReturn());
        returnSaham.setReturnMonthly(returnSaham.getReturnDaily()*30);
        returnSaham.setReturnYearly(returnSaham.getReturnMonthly()*12);


        if (saham == null) {
            saham = new HashMap<>();
        }

        saham.put(usertransactionid, returnSaham);
        return returnSaham;
    }
}


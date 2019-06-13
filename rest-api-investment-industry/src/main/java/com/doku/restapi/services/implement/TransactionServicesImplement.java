package com.doku.restapi.services.implement;

import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;
import com.doku.restapi.model.UserRequestResponse;
import com.doku.restapi.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service(value = "TransactionServices")
public class TransactionServicesImplement implements TransactionServices {

    @Autowired
    private UserServicesImplement userServicesImplement;

    DataSahamRequestResponse returnSaham;
    HashMap<String, DataSahamRequestResponse> saham;

//    DataSahamRequest dataSahamRequest;
//    HashMap<String, DataSahamRequest> sahamreq;
//    return sahamreg

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
        returnSaham.setMoneyBalance(userRequestResponse.getCurrentMoney());

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


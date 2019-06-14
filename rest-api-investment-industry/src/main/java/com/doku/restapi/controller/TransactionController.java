package com.doku.restapi.controller;

import com.doku.restapi.exception.DataNotFoundException;
import com.doku.restapi.model.DataSaham;
import com.doku.restapi.model.DataSahamRequest;
import com.doku.restapi.model.DataSahamRequestResponse;
import com.doku.restapi.model.DataSahamTransactionStatus;
import com.doku.restapi.services.TransactionServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("User Management API Documentation")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServices transactionServices;

    @ApiOperation(value = "Search User by ID")  //SEARCH BY ID
    @GetMapping(value = "/stock")
    public ResponseEntity getStock(DataSaham dataSaham){
        DataSahamRequest returnStock = transactionServices.getStock(dataSaham);
        if (returnStock != null){
            return new ResponseEntity<>(returnStock, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

    @ApiOperation(value = "Search User by ID")  //SEARCH BY ID
    @GetMapping(value = "/{userId}")
    public ResponseEntity calculateStock(@PathVariable String userId, DataSaham dataSaham){
        DataSahamRequestResponse returnTransaction = transactionServices.createTransaction(userId, dataSaham);
        if (returnTransaction != null){
            return new ResponseEntity<>(returnTransaction, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

    @ApiOperation(value = "Search User by ID")  //SEARCH BY ID
    @GetMapping(value = "/status/{userId}")
    public ResponseEntity updateTransaction(@PathVariable String userId, DataSahamRequest dataSahamRequest){
        DataSahamTransactionStatus returnTransaction = transactionServices.updateTransaction(userId, dataSahamRequest);
        if (returnTransaction != null){
            return new ResponseEntity<>(returnTransaction, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

}

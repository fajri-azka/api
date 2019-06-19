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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@Api("User Management API Documentation")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServices transactionServices;

    @ApiOperation(value = "CREATE STOCK")  //CREATE STOCK
    @PostMapping(value = "/stock")
    public ResponseEntity createStock(@Valid @RequestBody DataSaham dataSaham){
        DataSahamRequest returnStock = transactionServices.createStock(dataSaham);
        return new ResponseEntity<>(returnStock, HttpStatus.OK);
    }

    @ApiOperation(value = "GET STOCK BY ID")  //GET STOCK BY ID
    @GetMapping(value = "/stock/{stockId}")
    public ResponseEntity getStock(@PathVariable String stockId) {
        DataSahamRequest returnStock = transactionServices.getStock(stockId);
        if (returnStock != null) {
            return new ResponseEntity(returnStock, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

    @ApiOperation(value = "GET ALL STOCK")  //GET ALL STOCK
    @GetMapping(value = "/allStock")
    public ResponseEntity getAllStock() {
        Collection<DataSahamRequest> returnStock = transactionServices.getAllStock();
        return new ResponseEntity(returnStock, HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE TRANSACTION")  //CREATE TRANSACTION (INQUIRY)
    @PostMapping(value = "/inquiry")
    public ResponseEntity createTransaction(@Valid @RequestBody DataSahamRequestResponse dataSahamRequestResponse){
        DataSahamRequestResponse returnTransaction = transactionServices.createTransaction(dataSahamRequestResponse);
        if (returnTransaction != null){
            return new ResponseEntity<>(returnTransaction, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("Transaction Request (Inquiry) : FAILED, Data Not Found");
        }
    }

    @ApiOperation(value = "STATUS TRANSACTION")  //STATUS TRANSACTION
    @GetMapping(value = "/procestransaction/{userId}")
    public ResponseEntity updateTransaction(DataSahamRequestResponse dataSahamRequestResponse){
        DataSahamTransactionStatus returnTransaction = transactionServices.updateTransaction(dataSahamRequestResponse);
        if (returnTransaction != null){
            return new ResponseEntity<>(returnTransaction, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("Transaction Status : FAILED, Data Not Found");
        }
    }

//    @ApiOperation(value = "GET ALL TRANSACTION")  //GET ALL TRANSACTION
//    @GetMapping(value = "/alltransaction")
//    public ResponseEntity getAllTransaction() {
//        Collection <DataSahamTransactionStatus> returnTransaction = transactionServices.getAllTransaction();
//        if (returnTransaction != null) {
//            return new ResponseEntity(returnTransaction, HttpStatus.OK);
//        } else {
//            throw new DataNotFoundException();
//        }
//    }
}

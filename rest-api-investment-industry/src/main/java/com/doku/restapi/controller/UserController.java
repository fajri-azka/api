package com.doku.restapi.controller;

import com.doku.restapi.exception.DataNotFoundException;
import com.doku.restapi.model.UserRequest;
import com.doku.restapi.model.UserRequestResponse;
import com.doku.restapi.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@Api("User Management API Documentation")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServices userServices;


    @ApiOperation(value = "Create User")        //ADD USER
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity createUser(@Valid @RequestBody UserRequest userDetails){
        UserRequestResponse returnValue = userServices.createUser(userDetails);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }


    @ApiOperation(value = "Search User by ID")  //SEARCH BY ID
    @GetMapping(value = "/getById/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUser(@PathVariable String userId){
        UserRequestResponse returnValue = userServices.getUser(userId);
        if (returnValue != null){
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

    @ApiOperation(value = "Delete User")        //DELETE USER
    @DeleteMapping(value = "/deleteById/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        UserRequestResponse returnValue = userServices.deleteUser(userId);
        if (returnValue != null){
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

    @ApiOperation(value = "Shows All User")     //SHOW ALL USER
    @GetMapping(value = "/showUser")
    public ResponseEntity getAllUser(){
        Collection returnValue = userServices.getAllUser();
        if (returnValue != null ){
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException();
        }
    }

    @ApiOperation(value = "Shows All User")     //UPDATE USER
    @PatchMapping(value = "/updateUser/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId, @Valid @RequestBody UserRequestResponse updateUserRequest){

        UserRequestResponse returnValue = userServices.updateUser(userId, updateUserRequest);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }
}

package com.doku.restapi.controller;

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


@Api("User Management API Documentation")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServices userServices;

    @ApiOperation(value = "Create User")
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity createUser(@Valid @RequestBody UserRequest userDetails){
        UserRequestResponse returnValue = userServices.createUser(userDetails);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Search User by ID")
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

    public ResponseEntity getUser(@PathVariable String userId){
        UserRequestResponse returnValue = userServices.getUser(userId);
        if (returnValue != null){
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(returnValue, HttpStatus.NO_CONTENT);
        }
    }
}

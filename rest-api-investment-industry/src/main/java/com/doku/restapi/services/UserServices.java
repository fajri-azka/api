package com.doku.restapi.services;

import com.doku.restapi.model.UserRequest;
import com.doku.restapi.model.UserRequestResponse;

public interface UserServices {
    UserRequestResponse createUser(UserRequest userDetails);
    UserRequestResponse getUser(String userId);
}

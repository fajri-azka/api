package com.doku.restapi.services;

import com.doku.restapi.model.UserRequest;
import com.doku.restapi.model.UserRequestResponse;

import java.util.Collection;

public interface UserServices {
    UserRequestResponse createUser(UserRequest userDetails);
    UserRequestResponse getUser(String userId);
    UserRequestResponse deleteUser(String userId);
    Collection<UserRequestResponse> getAllUser();
    UserRequestResponse updateUser(String updateUserId, UserRequestResponse updateUserRequest);
}

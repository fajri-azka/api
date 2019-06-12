package com.doku.restapi.services.implement;

import com.doku.restapi.model.UserRequest;
import com.doku.restapi.model.UserRequestResponse;
import com.doku.restapi.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service(value = "UserServices")
public class UserServicesImplement implements UserServices  {

    UserRequestResponse returnValue;

    HashMap<String, UserRequestResponse> users;

    @Override
    public UserRequestResponse createUser(UserRequest userDetails){
        returnValue = new UserRequestResponse();

        returnValue.setUserId(userDetails.getUserId());
        String userId = returnValue.getUserId();

        returnValue.setFullName(userDetails.getFullName());
        returnValue.setUserAddress(userDetails.getUserAddress());
        returnValue.setCurrentMoney(userDetails.getCurrentMoney());
        returnValue.setKtpIdNumber(userDetails.getKtpIdNumber());

        if (users == null){
            users = new HashMap<>();
        }

        users.put(userId, returnValue);

        return returnValue;

    }

    public UserRequestResponse getUser (String userId){
        return users.get(userId);
    }

    public UserRequestResponse deleteUser (String userId){
        return users.remove(userId);
    }

    public Collection <UserRequestResponse> getAllUser (){
        return users.values();
    }

    public UserRequestResponse updateUser(String updateUserId, UserRequestResponse updateUserRequest){
        if (users.containsKey(updateUserId)){
            UserRequestResponse updateUserTemp = users.get(updateUserId);

            updateUserTemp.setFullName(updateUserRequest.getFullName());
            updateUserTemp.setUserAddress(updateUserRequest.getUserAddress());
            updateUserTemp.setCurrentMoney(updateUserRequest.getCurrentMoney());
            updateUserTemp.setKtpIdNumber(updateUserRequest.getKtpIdNumber());

            if(updateUserTemp != null) {
                users.put(updateUserId, updateUserTemp);
            }
        }
        return  users.get(updateUserId);
    }
}

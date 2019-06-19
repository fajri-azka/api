package com.doku.restapi;

import com.doku.restapi.controller.TransactionController;
import com.doku.restapi.controller.UserController;
import com.doku.restapi.exception.AppExceptionHandler;
import com.doku.restapi.exception.DataNotFoundException;
import com.doku.restapi.model.*;
import com.doku.restapi.services.TransactionServices;
import com.doku.restapi.services.UserServices;
import com.doku.restapi.services.implement.UserServicesImplement;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestApiInvestmentIndustryApplicationTests {

	@Autowired
	UserServices userServices;

	@Autowired
	TransactionServices transactionServices;

	@Autowired
	UserController userController;

	@Autowired
	TransactionController transactionController;

	@Autowired
	RestApiInvestmentIndustryApplication restApiInvestmentIndustryApplication;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void mainTest() {
		RestApiInvestmentIndustryApplication.main(new String[] {});
	}

	@Test
	public void createUserTest() {

		log.info("CREATE USER TEST");

		//UserRequestResponse testUserRes;
		//HashMap<String, UserRequestResponse> userTEST;

		UserRequest testuser = new UserRequest();
		testuser.setUserId(1);
		testuser.setFullName("TESTING");
		testuser.setUserAddress("KOTA TEST");
		testuser.setStockRequest(10);
		testuser.setCurrentMoney(100000);

		log.info("PARAMETER THAT WE POST");
		log.info("userId = " + testuser.getUserId());
		log.info("full name = " + testuser.getFullName());
		log.info("user address = " + testuser.getUserAddress());
		log.info("stock request = " + testuser.getStockRequest());
		log.info("current money = " + testuser.getCurrentMoney());

        ResponseEntity testCreateUserController = userController.createUser(testuser);
		UserRequestResponse testUserRes = userServices.createUser(testuser);

		assertThat(testUserRes.getUserId()).isEqualTo(testuser.getUserId());
		assertThat(testUserRes.getFullName()).isEqualTo(testuser.getFullName());
		assertThat(testUserRes.getUserAddress()).isEqualTo(testuser.getUserAddress());
		assertThat(testUserRes.getStockRequest()).isEqualTo(testuser.getStockRequest());
		assertThat(testUserRes.getCurrentMoney()).isEqualTo(testuser.getCurrentMoney());
        assertThat(testCreateUserController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

        //String userid = testUserRes.getUserId();

		//userTEST = new HashMap<>();
		//userTEST.put(userid, testUserRes);

		log.info("POSTED PARAMETER");
		log.info(testUserRes.toString());
	}

	@Test
	public void getUserTest() {
		createUserTest();

		log.info("GET USER TEST");

        ResponseEntity testGetUserController = userController.getUser("1");
		UserRequestResponse testGetUserServices = userServices.getUser("1");

		assertThat(testGetUserServices.getUserId()).isEqualTo(1);
		assertThat(testGetUserServices.getFullName()).isEqualTo("TESTING");
		assertThat(testGetUserServices.getUserAddress()).isEqualTo("KOTA TEST");
		assertThat(testGetUserServices.getStockRequest()).isEqualTo(10);
		assertThat(testGetUserServices.getCurrentMoney()).isEqualTo(100000);
        assertThat(testGetUserController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("POSTED PARAMETER");
		log.info(testGetUserServices.toString());
	}

	@Test
	public void getAllUserTest() {
		createUserTest();

		log.info("GET ALL USER TEST");
        ResponseEntity testGetAllUserController = userController.getAllUser();
		UserRequestResponse testGetAllUserServices = userServices.getUser("1");

		assertThat(userServices.getAllUser().toString()).isEqualTo("["+testGetAllUserServices+"]");
        assertThat(testGetAllUserController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("POSTED PARAMETER");
		log.info(testGetAllUserServices.toString());
	}

	@Test
	public void deleteUserTest() {
		createUserTest();

		log.info("DELETE USER TEST");
        ResponseEntity testDeleteUserController = userController.deleteUser("1");
		userServices.deleteUser("1");

		assertThat(userServices.getUser("1")).isNull();
        assertThat(testDeleteUserController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("POSTED PARAMETER");
	}


	@Test
	public void updateUserTest() {
		createUserTest();

		log.info("CREATE USER TEST");

		//UserRequestResponse testupdateuser;
		//HashMap<String, UserRequestResponse> userTEST;

		UserRequestResponse testupdateuser = new UserRequestResponse();
		testupdateuser.setUserId(1);
		testupdateuser.setFullName("TESTING");
		testupdateuser.setUserAddress("KOTA TEST");
		testupdateuser.setStockRequest(10);
		testupdateuser.setCurrentMoney(100000);

		log.info("PARAMETER THAT WE POST");
		log.info("userId = " + testupdateuser.getUserId());
		log.info("full name = " + testupdateuser.getFullName());
		log.info("user address = " + testupdateuser.getUserAddress());
		log.info("stock request = " + testupdateuser.getStockRequest());
		log.info("current money = " + testupdateuser.getCurrentMoney());

		UserRequestResponse testUserResServices = userServices.updateUser("1",testupdateuser);
        ResponseEntity testUserResController = userController.updateUser("1",testupdateuser);

		assertThat(testUserResServices.getUserId()).isEqualTo(testupdateuser.getUserId());
		assertThat(testUserResServices.getFullName()).isEqualTo(testupdateuser.getFullName());
		assertThat(testUserResServices.getUserAddress()).isEqualTo(testupdateuser.getUserAddress());
		assertThat(testUserResServices.getStockRequest()).isEqualTo(testupdateuser.getStockRequest());
		assertThat(testUserResServices.getCurrentMoney()).isEqualTo(testupdateuser.getCurrentMoney());

		assertThat(testUserResController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		String userid = Integer.toString(testUserResServices.getUserId());

		//userTEST = new HashMap<>();
		//userTEST.put(userid, testUserRes);

		log.info("POSTED PARAMETER");
		log.info(testUserResServices.toString());

	}

	@Test
	public  void  createStock() {
		DataSaham dataSaham = new DataSaham();
		dataSaham.setStockId("1");
		dataSaham.setStockName("XYZ");
		dataSaham.setStockPrice(1500);
		dataSaham.setStockDailyReturn(0.5);

        ResponseEntity testCreateStockController = transactionController.createStock(dataSaham);
		DataSahamRequest testCreateStockServices = transactionServices.createStock(dataSaham);

		assertThat(testCreateStockServices.getStockId()).isEqualTo(dataSaham.getStockId());
		assertThat(testCreateStockServices.getStockName()).isEqualTo(dataSaham.getStockName());
		assertThat(testCreateStockServices.getStockPrice()).isEqualTo(dataSaham.getStockPrice());
		assertThat(testCreateStockServices.getStockDailyReturn()).isEqualTo(dataSaham.getStockDailyReturn());
        assertThat(testCreateStockController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("POSTED PARAMETER");
		log.info(testCreateStockServices.toString());

	}

	@Test
	public void getStockTest() {
		createStock();
		log.info("CREATE USER TEST");

		//UserRequestResponse testUserRes;
		//HashMap<String, UserRequestResponse> userTEST;

		DataSaham testdatasaham = new DataSaham();
		testdatasaham.setStockId("1");
		testdatasaham.setStockName("XYZ");
		testdatasaham.setStockPrice(1500);
		testdatasaham.setStockDailyReturn(0.5);

		log.info("PARAMETER THAT WE POST");
		log.info("stockId = " + testdatasaham.getStockId());
		log.info("stock name = " + testdatasaham.getStockName());
		log.info("stock price = " + testdatasaham.getStockPrice());
		log.info("daily return = " + testdatasaham.getStockDailyReturn());

		String stockID = testdatasaham.getStockId();

        ResponseEntity testGetStockController = transactionController.getStock(stockID);
		DataSahamRequest testGetStockServices = transactionServices.getStock(stockID);

		assertThat(testGetStockServices.getStockId()).isEqualTo(testdatasaham.getStockId());
		assertThat(testGetStockServices.getStockName()).isEqualTo(testdatasaham.getStockName());
		assertThat(testGetStockServices.getStockPrice()).isEqualTo(testdatasaham.getStockPrice());
		assertThat(testGetStockServices.getStockDailyReturn()).isEqualTo(testdatasaham.getStockDailyReturn());
        assertThat(testGetStockController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		//String userid = testUserRes.getUserId();

		//userTEST = new HashMap<>();
		//userTEST.put(userid, testUserRes);

		log.info("POSTED PARAMETER");
		log.info(testGetStockServices.toString());
	}

	@Test
	public void getAllStockTest() {
		createStock();

		log.info("GET ALL STOCK TEST");
        ResponseEntity testGetAllStockController = transactionController.getAllStock();
		DataSahamRequest testGetAllStockServices = transactionServices.getStock("1");

		assertThat(transactionServices.getAllStock().toString()).isEqualTo("["+testGetAllStockServices+"]");
        assertThat(testGetAllStockController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("POSTED PARAMETER");
		log.info(testGetAllStockServices.toString());
	}

	@Test
	public void createTransactionTest() {
		createUserTest();
	    createStock();

		log.info("CREATE TRANSACTION TEST");

		UserRequestResponse testuserres = new UserRequestResponse();
		testuserres.setStockRequest(10);

		DataSaham testupdateuser = new DataSaham();
		testupdateuser.setStockDailyReturn(0.5);

		DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();
		dataSahamRequestResponse.setUserId("1");
		dataSahamRequestResponse.setFullName("TESTING");
		dataSahamRequestResponse.setStockId("1");
		dataSahamRequestResponse.setStockName("XYZ");
		dataSahamRequestResponse.setStockPrice(1500);

		log.info("PARAMETER THAT WE POST");
		log.info("userId = " + dataSahamRequestResponse.getUserId());
		log.info("full name = " + dataSahamRequestResponse.getFullName());
		log.info("stock request = " + testuserres.getStockRequest());

		log.info("stock id = " + dataSahamRequestResponse.getStockId());
		log.info("stock name = " + dataSahamRequestResponse.getStockName());
		log.info("stock price = " + dataSahamRequestResponse.getStockPrice());
		log.info("stock daily return = " + testupdateuser.getStockDailyReturn());

        ResponseEntity testCreateTransactionController = transactionController.createTransaction(dataSahamRequestResponse);
		DataSahamRequestResponse testCreateTransactionServices = transactionServices.createTransaction(dataSahamRequestResponse);

		assertThat(testCreateTransactionServices.getUserId()).isEqualTo(dataSahamRequestResponse.getUserId());
		assertThat(testCreateTransactionServices.getFullName()).isEqualTo(dataSahamRequestResponse.getFullName());
		assertThat(testCreateTransactionServices.getStockId()).isEqualTo(dataSahamRequestResponse.getStockId());
		assertThat(testCreateTransactionServices.getStockName()).isEqualTo(dataSahamRequestResponse.getStockName());
		assertThat(testCreateTransactionServices.getStockSheetRequest()).isEqualTo(testuserres.getStockRequest());
		assertThat(testCreateTransactionServices.getStockPrice()).isEqualTo(dataSahamRequestResponse.getStockPrice());
		assertThat(testCreateTransactionServices.getStockPriceTotal()).isEqualTo(dataSahamRequestResponse.getStockPrice()*testuserres.getStockRequest());
        assertThat(testCreateTransactionController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("POSTED PARAMETER");
		log.info(testCreateTransactionServices.toString());
	}


	@Test
	public void updateTransactionTest() {
        createTransactionTest();
        //createStock();
        createUserTest();
		log.info("UPDATE TRANSACTION TEST");

		UserRequestResponse testuserres =  userServices.getUser("1");
		//testuserres.setUserId(1);
		//testuserres.setStockRequest(10);
		//testuserres.setCurrentMoney(100000);

		String userID = Integer.toString(testuserres.getUserId());

		DataSahamRequest testupdateuser = new DataSahamRequest();
		testupdateuser.setStockId("1AAA");
		testupdateuser.setStockName("ABC");
		testupdateuser.setStockPrice(1500);
		testupdateuser.setStockDailyReturn(0.5);

		DataSaham dataSaham = new DataSaham();
		dataSaham.setStockPrice(1500);

		//DataSahamRequestResponse testupdateuserres = transactionServices.createTransaction(createTransactionTest());
		//testupdateuserres.setStockPriceTotal(testuserres.getStockRequest()*dataSaham.getStockPrice());

        DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();
        dataSahamRequestResponse.setUserId("1");
        dataSahamRequestResponse.setFullName("TESTING");
        dataSahamRequestResponse.setStockId("1");
        dataSahamRequestResponse.setStockName("XYZ");
        dataSahamRequestResponse.setStockPrice(1500);
        dataSahamRequestResponse.setStockPriceTotal(testuserres.getStockRequest()*dataSaham.getStockPrice());

        log.info("PARAMETER THAT WE POST");
        log.info("userId = " + dataSahamRequestResponse.getUserId());
        log.info("full name = " + dataSahamRequestResponse.getFullName());
        log.info("stock request = " + testuserres.getStockRequest());

        log.info("stock id = " + dataSahamRequestResponse.getStockId());
        log.info("stock name = " + dataSahamRequestResponse.getStockName());
        log.info("stock price = " + dataSahamRequestResponse.getStockPrice());
        log.info("stock daily return = " + testupdateuser.getStockDailyReturn());

		log.info("PARAMETER THAT WE POST");
		log.info("userId = " + userID);
		log.info("stock request = " + testuserres.getStockRequest());

		log.info("stock price = " + dataSaham.getStockPrice());
		log.info("stock price total = " + dataSahamRequestResponse.getStockPriceTotal());
		log.info("stock daily return = " + testupdateuser.getStockDailyReturn());

		DataSahamTransactionStatus testSahamTRX = new DataSahamTransactionStatus();
		testSahamTRX.setUserId("1");
		testSahamTRX.setMessageTransactionStatus("Transaction Success");
		testSahamTRX.setMoneyBalance(testuserres.getCurrentMoney()-dataSahamRequestResponse.getStockPriceTotal());
		testSahamTRX.setReturnDaily(dataSahamRequestResponse.getStockPriceTotal()*testupdateuser.getStockDailyReturn());
		testSahamTRX.setReturnMonthly(testSahamTRX.getReturnDaily()*30);
		testSahamTRX.setReturnYearly(testSahamTRX.getReturnMonthly()*12);

        ResponseEntity testUpdateTransactionController = transactionController.updateTransaction(dataSahamRequestResponse);
		DataSahamTransactionStatus testUpdateTransactionServices = transactionServices.updateTransaction(dataSahamRequestResponse);

		assertThat(testUpdateTransactionServices.getUserId()).isEqualTo(testSahamTRX.getUserId());
		assertThat(testUpdateTransactionServices.getMessageTransactionStatus()).isEqualTo(testSahamTRX.getMessageTransactionStatus());
		assertThat(testUpdateTransactionServices.getMoneyBalance()).isEqualTo(70000);
		assertThat(testUpdateTransactionServices.getReturnDaily()).isEqualTo(testSahamTRX.getReturnDaily());
		assertThat(testUpdateTransactionServices.getReturnMonthly()).isEqualTo(testSahamTRX.getReturnMonthly());
		assertThat(testUpdateTransactionServices.getReturnYearly()).isEqualTo(testSahamTRX.getReturnYearly());
        assertThat(testUpdateTransactionController.getStatusCode().is2xxSuccessful()).isEqualTo(true);

		log.info("PARAMETER THAT WE POST");
		log.info("userId = " + testUpdateTransactionServices.getUserId());
		log.info("message = " + testUpdateTransactionServices.getMessageTransactionStatus());
		log.info("money balance = " + testUpdateTransactionServices.getMoneyBalance());
		log.info("return daily = " + testUpdateTransactionServices.getReturnDaily());
		log.info("return monthly = " + testUpdateTransactionServices.getReturnMonthly());
		log.info("return yearly = " + testUpdateTransactionServices.getReturnYearly());

	}


	@Test(expected = DataNotFoundException.class)
	public void DataUserNotFound() {
		String id = "9";

		ResponseEntity getUser = userController.getUser(id);
		log.info(getUser.toString());
	}

	@Test(expected = NullPointerException.class)
	public void createUserInvalid() {

		UserRequest userDetails = null;

		ResponseEntity createUser = userController.createUser(userDetails);
		log.info(createUser.toString());
	}

	@Test(expected = DataNotFoundException.class)
	public void DeleteUserNotFound() {
		String id = "9";

		ResponseEntity deleteUser = userController.deleteUser(id);
		log.info(deleteUser.toString());
	}

	@Test(expected = NullPointerException.class)
	public void createStockInvalid() {

		DataSaham dataSaham = null;

		ResponseEntity createStock = transactionController.createStock(dataSaham);
		log.info(createStock.toString());
	}


	@Test(expected = DataNotFoundException.class)
	public void getStockDataNotFound() {
		String id = "9";

		ResponseEntity getStock = transactionController.getStock(id);
		log.info(getStock.toString());
	}


	@Test (expected = NullPointerException.class)
	public void createTransactionFail() {
        DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();
		String id = "99";

        ResponseEntity createStockController = transactionController.createTransaction(dataSahamRequestResponse);
		DataSahamRequestResponse createStockServices = transactionServices.createTransaction(dataSahamRequestResponse);

		log.info(createStockController.toString());
        log.info(createStockServices.toString());
	}

	@Test (expected = NullPointerException.class)
	public void transactionStatusFail() {

	    DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();
        dataSahamRequestResponse = null;

//        UserRequestResponse userRequestResponse = userServices.getUser("1");
//        userRequestResponse.setCurrentMoney(10000);

        ResponseEntity transactionStatusController = transactionController.updateTransaction(dataSahamRequestResponse);
		DataSahamTransactionStatus transactionStatusServices = transactionServices.updateTransaction(dataSahamRequestResponse);

		log.info(transactionStatusController.toString());
        log.info(transactionStatusServices.toString());
	}

    @Test (expected = DataNotFoundException.class)
    public void transactionStatusMoneyNotEnough() {

        DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();

        UserRequestResponse userRequestResponse = userServices.getUser("1");
        userRequestResponse.setCurrentMoney(10000);

        ResponseEntity transactionStatusController = transactionController.updateTransaction(dataSahamRequestResponse);
        DataSahamTransactionStatus transactionStatusServices = transactionServices.updateTransaction(dataSahamRequestResponse);

        log.info(transactionStatusController.toString());
        log.info(transactionStatusServices.toString());
    }

}


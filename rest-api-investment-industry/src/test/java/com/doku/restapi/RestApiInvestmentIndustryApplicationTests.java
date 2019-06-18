//package com.doku.restapi;
//
//import com.doku.restapi.controller.TransactionController;
//import com.doku.restapi.controller.UserController;
//import com.doku.restapi.exception.AppExceptionHandler;
//import com.doku.restapi.exception.DataNotFoundException;
//import com.doku.restapi.model.*;
//import com.doku.restapi.services.TransactionServices;
//import com.doku.restapi.services.UserServices;
//import com.doku.restapi.services.implement.UserServicesImplement;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.juli.logging.Log;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.logging.Logger;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class RestApiInvestmentIndustryApplicationTests {
//
//	@Autowired
//	UserServices userServices;
//
//	@Autowired
//	TransactionServices transactionServices;
//
//	@Autowired
//	UserController userController;
//
//	@Autowired
//	TransactionController transactionController;
//
//	@Rule
//	public ExpectedException exceptionRule = ExpectedException.none();
//
//	@Test
//	public void createUserTest() {
//
//		log.info("CREATE USER TEST");
//
//		//UserRequestResponse testUserRes;
//		//HashMap<String, UserRequestResponse> userTEST;
//
//		UserRequest testuser = new UserRequest();
//		testuser.setUserId(1);
//		testuser.setFullName("TESTING");
//		testuser.setUserAddress("KOTA TEST");
//		testuser.setStockRequest(10);
//		testuser.setCurrentMoney(100000);
//
//		log.info("PARAMETER THAT WE POST");
//		log.info("userId = " + testuser.getUserId());
//		log.info("full name = " + testuser.getFullName());
//		log.info("user address = " + testuser.getUserAddress());
//		log.info("stock request = " + testuser.getStockRequest());
//		log.info("current money = " + testuser.getCurrentMoney());
//
//		UserRequestResponse testUserRes = userServices.createUser(testuser);
//
//		assertThat(testUserRes.getUserId()).isEqualTo(testuser.getUserId());
//		assertThat(testUserRes.getFullName()).isEqualTo(testuser.getFullName());
//		assertThat(testUserRes.getUserAddress()).isEqualTo(testuser.getUserAddress());
//		assertThat(testUserRes.getStockRequest()).isEqualTo(testuser.getStockRequest());
//		assertThat(testUserRes.getCurrentMoney()).isEqualTo(testuser.getCurrentMoney());
//
//		//String userid = testUserRes.getUserId();
//
//		//userTEST = new HashMap<>();
//		//userTEST.put(userid, testUserRes);
//
//		log.info("POSTED PARAMETER");
//		log.info(testUserRes.toString());
//	}
//
//	@Test
//	public void getUserTest() {
//		createUserTest();
//
//		log.info("GET USER TEST");
//
//		UserRequestResponse testGetUser = userServices.getUser("1");
//
//		assertThat(testGetUser.getUserId()).isEqualTo(1);
//		assertThat(testGetUser.getFullName()).isEqualTo("TESTING");
//		assertThat(testGetUser.getUserAddress()).isEqualTo("KOTA TEST");
//		assertThat(testGetUser.getStockRequest()).isEqualTo(10);
//		assertThat(testGetUser.getCurrentMoney()).isEqualTo(100000);
//
//		log.info("POSTED PARAMETER");
//		log.info(testGetUser.toString());
//	}
//
//	@Test
//	public void getAllUserTest() {
//		createUserTest();
//
//		log.info("GET ALL USER TEST");
//		UserRequestResponse testGetUser = userServices.getUser("1");
//		assertThat(userServices.getAllUser().toString()).isEqualTo("["+testGetUser+"]");
//
//		log.info("POSTED PARAMETER");
//		log.info(testGetUser.toString());
//	}
//
//	@Test
//	public void deleteUserTest() {
//		createUserTest();
//
//		log.info("DELETE USER TEST");
//		userServices.deleteUser("1");
//		assertThat(userServices.getUser("1")).isNull();
//
//		log.info("POSTED PARAMETER");
//	}
//
//
//	@Test
//	public void updateUserTest() {
//		createUserTest();
//
//		log.info("CREATE USER TEST");
//
//		//UserRequestResponse testupdateuser;
//		//HashMap<String, UserRequestResponse> userTEST;
//
//		UserRequestResponse testupdateuser = new UserRequestResponse();
//		testupdateuser.setUserId(1);
//		testupdateuser.setFullName("TESTING");
//		testupdateuser.setUserAddress("KOTA TEST");
//		testupdateuser.setStockRequest(10);
//		testupdateuser.setCurrentMoney(100000);
//
//		log.info("PARAMETER THAT WE POST");
//		log.info("userId = " + testupdateuser.getUserId());
//		log.info("full name = " + testupdateuser.getFullName());
//		log.info("user address = " + testupdateuser.getUserAddress());
//		log.info("stock request = " + testupdateuser.getStockRequest());
//		log.info("current money = " + testupdateuser.getCurrentMoney());
//
//		UserRequestResponse testUserRes = userServices.updateUser("1",testupdateuser);
//
//		assertThat(testUserRes.getUserId()).isEqualTo(testupdateuser.getUserId());
//		assertThat(testUserRes.getFullName()).isEqualTo(testupdateuser.getFullName());
//		assertThat(testUserRes.getUserAddress()).isEqualTo(testupdateuser.getUserAddress());
//		assertThat(testUserRes.getStockRequest()).isEqualTo(testupdateuser.getStockRequest());
//		assertThat(testUserRes.getCurrentMoney()).isEqualTo(testupdateuser.getCurrentMoney());
//
//		String userid = Integer.toString(testUserRes.getUserId());
//
//		//userTEST = new HashMap<>();
//		//userTEST.put(userid, testUserRes);
//
//		log.info("POSTED PARAMETER");
//		log.info(testUserRes.toString());
//	}
//
//	@Test
//	public  void  createStock() {
//		DataSaham dataSaham = new DataSaham();
//		dataSaham.setStockId("1");
//		dataSaham.setStockName("XYZ");
//		dataSaham.setStockPrice(1500);
//		dataSaham.setStockDailyReturn(0.5);
//
//		DataSahamRequest createStockTest = transactionServices.createStock(dataSaham);
//
//		assertThat(createStockTest.getStockId()).isEqualTo(dataSaham.getStockId());
//		assertThat(createStockTest.getStockName()).isEqualTo(dataSaham.getStockName());
//		assertThat(createStockTest.getStockPrice()).isEqualTo(dataSaham.getStockPrice());
//		assertThat(createStockTest.getStockDailyReturn()).isEqualTo(dataSaham.getStockDailyReturn());
//
//		log.info("POSTED PARAMETER");
//		log.info(createStockTest.toString());
//
//	}
//
//	@Test
//	public void getStockTest() {
//		createStock();
//		log.info("CREATE USER TEST");
//
//		//UserRequestResponse testUserRes;
//		//HashMap<String, UserRequestResponse> userTEST;
//
//		DataSaham testdatasaham = new DataSaham();
//		testdatasaham.setStockId("1");
//		testdatasaham.setStockName("XYZ");
//		testdatasaham.setStockPrice(1500);
//		testdatasaham.setStockDailyReturn(0.5);
//
//		log.info("PARAMETER THAT WE POST");
//		log.info("stockId = " + testdatasaham.getStockId());
//		log.info("stock name = " + testdatasaham.getStockName());
//		log.info("stock price = " + testdatasaham.getStockPrice());
//		log.info("daily return = " + testdatasaham.getStockDailyReturn());
//
//		DataSahamRequest testSahamRes = transactionServices.getStock(testdatasaham.getStockId());
//
//		assertThat(testSahamRes.getStockId()).isEqualTo(testdatasaham.getStockId());
//		assertThat(testSahamRes.getStockName()).isEqualTo(testdatasaham.getStockName());
//		assertThat(testSahamRes.getStockPrice()).isEqualTo(testdatasaham.getStockPrice());
//		assertThat(testSahamRes.getStockDailyReturn()).isEqualTo(testdatasaham.getStockDailyReturn());
//
//		//String userid = testUserRes.getUserId();
//
//		//userTEST = new HashMap<>();
//		//userTEST.put(userid, testUserRes);
//
//		log.info("POSTED PARAMETER");
//		log.info(testSahamRes.toString());
//	}
//
//
//	@Test
//	public void getAllStockTest() {
//		createStock();
//
//		log.info("GET ALL STOCK TEST");
//		DataSahamRequest dataSahamRequest = transactionServices.getStock("1");
//		assertThat(transactionServices.getAllStock().toString()).isEqualTo("["+dataSahamRequest+"]");
//
//		log.info("POSTED PARAMETER");
//		log.info(dataSahamRequest.toString());
//	}
//
//	@Test
//	public void createTransactionTest() {
//		createStock();
//
//		log.info("CREATE TRANSACTION TEST");
//
//		//UserRequestResponse testUserRes;
//		//HashMap<String, UserRequestResponse> userTEST;
//
//		//UserRequestResponse testupdateuser;
//		//HashMap<String, UserRequestResponse> userTEST;
//		UserRequestResponse testuserres = new UserRequestResponse();
//		//testuserres.setUserId(1);
//		//testuserres.setFullName("TESTING");
//		testuserres.setStockRequest(10);
//
//		DataSaham testupdateuser = new DataSaham();
//		testupdateuser.setStockDailyReturn(0.5);
//
//		DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();
//		dataSahamRequestResponse.setUserId("1");
//		dataSahamRequestResponse.setFullName("TESTING");
//		dataSahamRequestResponse.setStockId("1");
//		dataSahamRequestResponse.setStockName("XYZ");
//		dataSahamRequestResponse.setStockPrice(1500);
//
//		log.info("PARAMETER THAT WE POST");
//		log.info("userId = " + dataSahamRequestResponse.getUserId());
//		log.info("full name = " + dataSahamRequestResponse.getFullName());
//		log.info("stock request = " + testuserres.getStockRequest());
//
//		log.info("stock id = " + dataSahamRequestResponse.getStockId());
//		log.info("stock name = " + dataSahamRequestResponse.getStockName());
//		log.info("stock price = " + dataSahamRequestResponse.getStockPrice());
//		log.info("stock daily return = " + testupdateuser.getStockDailyReturn());
//
//		DataSahamRequestResponse testSahamRes = transactionServices.createTransaction(dataSahamRequestResponse);
//
//		assertThat(testSahamRes.getUserId()).isEqualTo(dataSahamRequestResponse.getUserId());
//		assertThat(testSahamRes.getFullName()).isEqualTo(dataSahamRequestResponse.getFullName());
//		assertThat(testSahamRes.getStockId()).isEqualTo(dataSahamRequestResponse.getStockId());
//		assertThat(testSahamRes.getStockName()).isEqualTo(dataSahamRequestResponse.getStockName());
//		assertThat(testSahamRes.getStockSheetRequest()).isEqualTo(testuserres.getStockRequest());
//		assertThat(testSahamRes.getStockPrice()).isEqualTo(dataSahamRequestResponse.getStockPrice());
//		assertThat(testSahamRes.getStockPriceTotal()).isEqualTo(dataSahamRequestResponse.getStockPrice()*testuserres.getStockRequest());
//
//		//String userid = testUserRes.getUserId();
//
//		//userTEST = new HashMap<>();
//		//userTEST.put(userid, testUserRes);
//
//		log.info("PARAMETER THAT WE POST");
//		log.info("userId = " + testSahamRes.getUserId());
//		log.info("full name = " + testSahamRes.getFullName());
//		log.info("stock request = " + testSahamRes.getStockId());
//		log.info("stock id = " + testSahamRes.getStockName());
//		log.info("stock name = " + testSahamRes.getStockName());
//		log.info("stock price = " + testSahamRes.getStockPrice());
//		log.info("stock price total = " + testSahamRes.getStockPriceTotal());
//
//		log.info("POSTED PARAMETER");
//		//log.info(testSahamRes);
//	}
//
////	@Test
////	public void updateTransactionTest() {
////
////		log.info("UPDATE TRANSACTION TEST");
////
////		UserRequestResponse testuserres = userServices.getUser("1");
////		testuserres.setUserId("1");
////		testuserres.setStockRequest(10);
////		testuserres.setCurrentMoney(100000);
////
////		DataSahamRequest testupdateuser = new DataSahamRequest();
////		testupdateuser.setStockId("1AAA");
////		testupdateuser.setStockName("ABC");
////		testupdateuser.setStockPrice(1500);
////		testupdateuser.setStockDailyReturn(0.5);
////
////		DataSaham dataSaham = new DataSaham();
////		dataSaham.setStockPrice(1500);
////
////		DataSahamRequestResponse testupdateuserres = new DataSahamRequestResponse();
////		testupdateuserres.setStockPriceTotal(testuserres.getStockRequest()*dataSaham.getStockPrice());
////
////		log.info("PARAMETER THAT WE POST");
////		log.info("userId = " + testuserres.getUserId());
////		log.info("stock request = " + testuserres.getStockRequest());
////
////		log.info("stock price = " + dataSaham.getStockPrice());
////		log.info("stock price total = " + testupdateuserres.getStockPriceTotal());
////		log.info("stock daily return = " + testupdateuser.getStockDailyReturn());
////
////		DataSahamTransactionStatus testSahamTRX = new DataSahamTransactionStatus();
////		testSahamTRX.getUserId()).isEqualTo(testuserres.getUserId());
////		testSahamTRX.getMessageTransactionStatus()).isEqualTo("Transaction Success");
////		testSahamTRX.getMoneyBalance()).isEqualTo(testuserres.getCurrentMoney()-testupdateuserres.getStockPriceTotal());
////		testSahamTRX.getReturnDaily()).isEqualTo(testupdateuserres.getStockPriceTotal()*testupdateuser.getStockDailyReturn());
////		testSahamTRX.getReturnMonthly()).isEqualTo(testSahamTRX.getReturnDaily()*30);
////		testSahamTRX.getReturnYearly()).isEqualTo(testSahamTRX.getReturnMonthly()*12);
////
////
////		DataSahamTransactionStatus testSahamTR = transactionServices.updateTransaction("1",testupdateuser);
////
////		assertThat(testSahamTR.getUserId()).isEqualTo(testuserres.getUserId());
////		assertThat(testSahamTR.getMessageTransactionStatus()).isEqualTo("Transaction Success");
////		assertThat(testSahamTR.getMoneyBalance()).isEqualTo(testuserres.getCurrentMoney()-testupdateuserres.getStockPriceTotal());
////		assertThat(testSahamTR.getReturnDaily()).isEqualTo(testupdateuserres.getStockPriceTotal()*testupdateuser.getStockDailyReturn());
////		assertThat(testSahamTR.getReturnMonthly()).isEqualTo(testSahamTRX.getReturnDaily()*30);
////		assertThat(testSahamTR.getReturnYearly()).isEqualTo(testSahamTRX.getReturnMonthly()*12);
////
////		log.info("PARAMETER THAT WE POST");
////		log.info("userId = " + testSahamTRX.getUserId());
////		log.info("message = " + testSahamTRX.getMessageTransactionStatus());
////		log.info("money balance = " + testSahamTRX.getMoneyBalance());
////		log.info("return daily = " + testSahamTRX.getReturnDaily());
////		log.info("return monthly = " + testSahamTRX.getReturnMonthly());
////		log.info("return yearly = " + testSahamTRX.getReturnYearly());
////
////	}
//
//	@Test(expected = DataNotFoundException.class)
//	public void DataUserNotFound() {
//		String id = "9";
//
//		ResponseEntity getUser = userController.getUser(id);
//		log.info(getUser.toString());
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void createUserInvalid() {
//
//		UserRequest userDetails = null;
//
//		ResponseEntity getUser = userController.createUser(userDetails);
//		log.info(getUser.toString());
//	}
//
//	@Test(expected = DataNotFoundException.class)
//	public void DeleteUserNotFound() {
//		String id = "9";
//
//		ResponseEntity deleteUser = userController.deleteUser(id);
//		log.info(deleteUser.toString());
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void createStockInvalid() {
//
//		DataSaham dataSaham = null;
//
//		ResponseEntity createStock = transactionController.createStock(dataSaham);
//		log.info(createStock.toString());
//	}
//
//
//	@Test(expected = DataNotFoundException.class)
//	public void getStockDataNotFound() {
//		String id = "9";
//
//		ResponseEntity getStock = transactionController.getStock(id);
//		log.info(getStock.toString());
//	}
//
//
//	@Test (expected = NullPointerException.class)
//	public void createTransactionFail() {
//        DataSahamRequestResponse dataSahamRequestResponse = new DataSahamRequestResponse();
//		String id = "99";
//
//		DataSahamRequestResponse createStock = transactionServices.createTransaction(dataSahamRequestResponse);
//		log.info(createStock.toString());
//	}
//
//	@Test (expected = NullPointerException.class)
//	public void transactionStatusFail() {
//		DataSahamRequest dataSahamRequest = new DataSahamRequest();
//		String id = "99";
//
//		DataSahamTransactionStatus getStock = transactionServices.updateTransaction(id, dataSahamRequest);
//		log.info(getStock.toString());
//	}
//
////	@Test (expected = DataNotFoundException.class)
////	public void showSahamFail() {
////		DataSaham dataSaham = new DataSaham();
////		dataSaham.setStockId(null);
////
////		DataSahamRequest getStock = transactionServices.getStock(dataSaham);
////		log.info(getStock.toString());
////	}
//
////	@Test (expected = DataNotFoundException.class)
////	public void showSahamFail() {
////		DataSaham dataSaham = new DataSaham();
////		dataSaham.setStockId(null);
////
////		DataSahamRequest getStock = transactionServices.getStock(dataSaham);
////		log.info(getStock.toString());
////	}
//
//}
//

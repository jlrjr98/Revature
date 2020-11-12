package com.app.bank.service.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.model.TransactionLog;
import com.app.bank.service.BankSearchService;
import com.app.bank.service.impl.BankSearchServiceImpl;

//... other imports and annotations
@ExtendWith(CustomParameterResolver.class) // your custom parameter resolver, see below
class BankSearchServiceImplTest {
	
	private static BankSearchService bankSearchService;

	@BeforeAll
	public static void setUpSearchService() {
		bankSearchService = new BankSearchServiceImpl();
	}
	
	@Test
	void testGetAccount(Server server, Server server1) throws BusinessException {
		String user_id = "VBfe";
		String first_name = "Olimpia";
		String last_name = "Dreschler";
		int account_id = 1557;
		BigDecimal balance = new BigDecimal("179170.00");
		BigDecimal transfer = new BigDecimal("0.00");
		int transfer_account_id = 0;
		
		
		List<Account> expected = new ArrayList<>();
		Account expected_account = new Account(user_id, first_name, last_name, account_id, balance, transfer, transfer_account_id);
		expected.add(expected_account);
		
		List<Account> actual = bankSearchService.getAccount("VBfe", "853E5IpHih");
		
		boolean b = expected.equals(actual);
		
		assertEquals(true, b);		
	}
	
	@Test
	void testGetBalance(Server server) throws BusinessException {
		int account_id = 3237;
		BigDecimal balance = bankSearchService.getBalance(account_id);
		BigDecimal expected = new BigDecimal("947777.86");
		
		assertEquals(expected, balance);
	}
	
	
	@Test
	void testVerifyAccountId(Server server, Server server1) throws BusinessException {
		int verify_account_id = bankSearchService.verifyAccountId("YkNY", 3028);
		
		assertEquals(3028, verify_account_id);
	}
	
	@Test
	void testGetTransfer(Server server) throws BusinessException{
		
		int transfer_account_id = 2706;		
		BigDecimal transfer_recieve = bankSearchService.getTransfer(transfer_account_id);
		BigDecimal expected = new BigDecimal("100.00");
		
		assertEquals(expected, transfer_recieve);
	}
	
	@Test
	void testVerifyTransferAccountId(Server server, Server server1) throws BusinessException {
		
		String user_id = "YkNY";
		int transfer_account_id = 2706;
		int verify_transfer_account_id = bankSearchService.verifyTransferAccountId(user_id, transfer_account_id);
		int expected = 2706;
		
		assertEquals(expected, verify_transfer_account_id);
	}
	
	@Test
	void testVerifyUserId(Server server) throws BusinessException {
		
		int account_id = 3028;
		String user_id = bankSearchService.verifyUserId(account_id);
		String expected = "YkNY";
		
		assertEquals(expected, user_id);
	}
	
	@Test
	void testVerifyUserId2(Server server) throws BusinessException {
		
		String last_name = "Onepunchman";
		String user_password = "hero4fun";
		String user_id = bankSearchService.verifyUserId(last_name, user_password);
		String expected = "OJyW";
		
		assertEquals(expected, user_id);
	}
	
	@Test
	void testGetEmployee(Server server, Server server1) throws BusinessException {
		
		Employee expected = new Employee("oi95k", "Ora", "Le Fleming");
		
		Employee employee = bankSearchService.getEmployee("oi95k", "Ou6BrG8g0tg");
		
		boolean b = expected.equals(employee);
		
		assertEquals(true, b);
	}
	
	@Test
	void testVerifyAccountId(Server server) throws BusinessException {
	
		List<Integer> expected = new ArrayList<>();
		Integer integer = new Integer(1557);
		expected.add(integer);
		
		List<Integer> account_id_list = bankSearchService.verifyAccountId("VBfe");
		
		boolean b = expected.equals(account_id_list);
		
		assertEquals(true, b);
	}
	
	@Test
	void testGetTransactionLogList() throws BusinessException, ParseException {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timestamp = fmt.parse("2020-11-08 00:00:00.0");
		
		String user_subject = "VBfe";
		int account_subject = 1557;
		String transaction_action = "WITHDRAWAL";
		BigDecimal funds = new BigDecimal("9.06");
		String user_object = "0";
		int account_object = 0;
		
		TransactionLog expected = new TransactionLog(timestamp, user_subject, account_subject, transaction_action, funds, user_object, account_object);
		
		List<TransactionLog> transactionLogList = bankSearchService.getTransactionLogList();				
		
		TransactionLog actual = transactionLogList.get(0);
		
		boolean b = expected.equals(actual);
		
		assertEquals(true, b);		
	}		
}

class Server {
	private String host = "http://dev-dev/";
	private String endpoint = "people";
	public String getHost() {
	  return host;
	}
    public String getEndpoint() {
      return endpoint;
    }
  }
class CustomParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      return (parameterContext.getParameter().getType() == Server.class);
    }
    @Override
    public Object resolveParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      return new Server();
    }
  }

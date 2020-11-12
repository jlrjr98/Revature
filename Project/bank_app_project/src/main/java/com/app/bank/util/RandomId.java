package com.app.bank.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomId {
	
	public String randomUserId() {	
		
		int leftLimit = 48;
	    int rightLimit = 122;
	    int targetStringLength = 4;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;  	    
	}
 
	public int randomAccountId() {
		int randomAccountId = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
		return randomAccountId;
	}	
}

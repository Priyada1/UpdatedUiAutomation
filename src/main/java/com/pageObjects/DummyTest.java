package com.pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DummyTest {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String dateOpenedOrDisbursed_asString="15121990";
	
			System.out.println(new SimpleDateFormat("ddMMyyyy").parse(dateOpenedOrDisbursed_asString));
		
		
	}

}

package com.cast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public String getRandomNumber(int i) {
	 Random rdom = new Random();
	String ranDomNumber = rdom.toString();
	return ranDomNumber;
	}
	
	public String getRandomString(){
	Random rs = new Random();
	String rst = rs.toString(); return rst;
	}
	
	public String getSystemDateYYYYMMDD() {
		Date datObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
	   String date= 	sdf.format(datObj);
		return date;}
	
	public String getRequiredDateYYYYMMDD(int days) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqdt = sim.format(cal.getTime());
		return reqdt;
	}

	public String ranDomNumber() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

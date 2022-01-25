package com.crm.comcast.genericutility;

import java.util.Date;
import java.util.Random;

/**
 *   class contains java specific libraries which includes get System date & get random number etc
 * @author Deepak HR
 *
 */
public class JavaUtlity {

	
	/**
	 * its generate random number with in the limit of 10000
	 * @return int
	 */
	public int getRandomNumber() {
		Random ran = new Random();
		int intRanDomNum = ran.nextInt(10000);
		return intRanDomNum;
	}
	/**
	 * its generate date in the format of YYYY-MM-DD
	 * @return date_YYYYMMDD
	 */
	public String getDateFormate() {
		Date dateObj = new Date();
		int date = dateObj.getDate();
		int month = dateObj.getMonth() + 1;
		String year = dateObj.toString().split(" ")[5];

		String date_YYYYMMDD = year + "-" + month + "-" + date;
		return date_YYYYMMDD;
	}
	
}

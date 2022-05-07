package com.alv.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

	public static String goToFormat(Date date, String format) {
		String pattern = format;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateFormatted = simpleDateFormat.format(date);		
		return dateFormatted;
	}
	public static Date addDays(Date origin, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(origin);
		c.add(Calendar.DATE, 1);  // number of days to add
		return c.getTime();
	}
	
	public static Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, day);
		return c.getTime();
	}
}

package com.lacosdaalegria.transparencia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DataUtil {
	
	public static Date diasAtras(Integer dias) {
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.DAY_OF_MONTH, -dias);
		return calendar.getTime();
	}
	
	public static Date parseDate(String date, String format) {
	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}

package com.security.manage;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {
	public static void main(String[] args) throws ParseException {
		String s = "2016-06-15";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(s);
		System.out.println(d);
	}
}

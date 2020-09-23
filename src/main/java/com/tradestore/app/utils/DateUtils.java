package com.tradestore.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static boolean isMaturityDateOld(String date) {
		LocalDate maturityDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate todaysDate = LocalDate.now();
		return maturityDate.isBefore(todaysDate);
	}
	
	public static boolean isMaturityDateExpired(String date) {
		LocalDate maturityDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return LocalDate.now().isBefore(maturityDate);
	}
}

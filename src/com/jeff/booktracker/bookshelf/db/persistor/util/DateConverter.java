package com.jeff.booktracker.bookshelf.db.persistor.util;

import java.sql.Date;
import java.time.LocalDate;

public class DateConverter {

	/**
	 * Convert from LocalDate to Date.
	 * 
	 * @param localDate
	 * @return Date
	 */
	public Date toSQL(LocalDate localDate) {
		return Date.valueOf(localDate);
	}

	/**
	 * Convert from Date to LocalDate.
	 * 
	 * @param date
	 * @return LocalDate
	 */
	public LocalDate toLocalDate(Date date) {
		return date.toLocalDate();
	}

}

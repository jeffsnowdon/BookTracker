package com.jeff.booktracker.bookshelf.db.persistor.util;

import java.sql.Date;
import java.time.LocalDate;

public class DateConverter {

	public Date toSQL(LocalDate localDate) {
		return Date.valueOf(localDate);
	}

	public LocalDate toLocalDate(Date date) {
		return date.toLocalDate();
	}

}

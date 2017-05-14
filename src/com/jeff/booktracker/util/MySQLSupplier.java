package com.jeff.booktracker.util;

import java.sql.Connection;
import java.util.function.Supplier;

public class MySQLSupplier implements Supplier<Connection> {

	private Connection connection;

	@Override
	public Connection get() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}

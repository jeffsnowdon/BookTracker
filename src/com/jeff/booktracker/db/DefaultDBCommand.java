package com.jeff.booktracker.db;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultDBCommand implements DBCommand {

	private Connection mysql;
	private String mysqlFilePath;

	public DefaultDBCommand(Connection mysql, String mysqlFilePath) {
		super();
		this.mysql = mysql;
		this.mysqlFilePath = mysqlFilePath;
	}

	@Override
	public void execute() {
		Statement statement;
		try {
			statement = mysql.createStatement();
			statement.execute(readFileFromClasspath(mysqlFilePath));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public String readFileFromClasspath(final String fileName) throws IOException, URISyntaxException {
		return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI())));
	}

}

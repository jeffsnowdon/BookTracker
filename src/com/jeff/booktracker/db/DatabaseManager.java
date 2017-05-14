package com.jeff.booktracker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.jeff.booktracker.lifecycle.Disposable;
import com.jeff.booktracker.lifecycle.Initializable;
import com.jeff.booktracker.main.util.MySQLSupplier;

public class DatabaseManager implements Initializable, Disposable, BeanFactoryAware {

	private static final String DATABASE_PATH = "C:\\Code\\BookTracker\\eclipse\\workspace\\BookTracker\\mysql\\bin\\";
	private static final String DATABASE_START_CMD = "mysqld.exe";
	private static final String DATABASE_SHUTDOWN_CMD = "taskkill";
	private Process databaseProcess;
	private BeanFactory beanFactory;
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
	private Connection connection = null;

	@Override
	public void init() {
		ProcessBuilder pb = new ProcessBuilder(DATABASE_PATH + DATABASE_START_CMD);
		try {
			databaseProcess = pb.start();
		} catch (Exception e) {
			logger.severe("Error starting database: " + e.toString());
		}

		while (!dbReady()) {
			logger.severe("Database not yet started.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		initMySQL();
		runDBCommands();
	}

	private boolean dbReady() {
		boolean dbReady = true;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
			// Succes!
		} catch (SQLException e) {
			// Fail!
			dbReady = false;
		}
		return dbReady;
	}

	private void initMySQL() {
		MySQLSupplier mysqlSupplier = (MySQLSupplier) beanFactory.getBean("mySQLSupplier");
		mysqlSupplier.setConnection(connection);
	}

	private void runDBCommands() {
		DBInitializer dbInitializer = (DBInitializer) beanFactory.getBean("dbInitializer");
		dbInitializer.init();
	}

	@Override
	public void dispose() {
		if (databaseProcess != null) {
			// database process was created my this application
			databaseProcess.destroy();
		}
		// try to kill a potential dangling database process
		ProcessBuilder pb = new ProcessBuilder(DATABASE_SHUTDOWN_CMD, "/im", DATABASE_START_CMD, "/f");
		try {
			pb.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}

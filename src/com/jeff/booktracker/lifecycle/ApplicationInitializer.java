package com.jeff.booktracker.lifecycle;

import com.jeff.booktracker.db.DatabaseManager;

public class ApplicationInitializer implements Initializable {

	private DatabaseManager databaseManager;

	public ApplicationInitializer(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}

	private void startupApplication() {

	}

	private void shutdownApplication() {
		shutdownDatabase();
	}

	private void shutdownDatabase() {
		databaseManager.dispose();
	}

	private void initDatabase() {
		databaseManager.init();
	}

	@Override
	public void init() {
		initDatabase();
		startupApplication();
		shutdownApplication();
	}

}

package com.jeff.booktracker.lifecycle;

import com.jeff.booktracker.db.DatabaseManager;
import com.jeff.booktracker.lifecycle.ui.GUIInitializer;

public class ApplicationInitializer implements Initializable {

	private DatabaseManager databaseManager;
	private GUIInitializer guiInitializer;

	public ApplicationInitializer(DatabaseManager databaseManager, GUIInitializer guiInitializer) {
		this.databaseManager = databaseManager;
		this.guiInitializer = guiInitializer;

	}

	private void startupApplication() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				shutdownApplication();
			}
		}));
		initGUI();
	}

	private void initGUI() {
		guiInitializer.init();
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
	}

}

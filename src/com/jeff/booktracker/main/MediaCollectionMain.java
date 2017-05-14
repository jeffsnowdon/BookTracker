package com.jeff.booktracker.main;

import java.io.InputStream;
import java.util.logging.LogManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jeff.booktracker.db.DatabaseManager;
import com.jeff.booktracker.lifecycle.Initializable;

public class MediaCollectionMain {

	private ApplicationContext applicationContext;
	private DatabaseManager databaseManager;

	public static void main(String[] args) {
		new MediaCollectionMain();
	}

	static {
		MediaCollectionMain.class.getResourceAsStream("/logging.properties");
		try (InputStream inputStream = MediaCollectionMain.class.getResourceAsStream("/logging.properties")) {
			if (inputStream != null)
				LogManager.getLogManager().readConfiguration(inputStream);
		} catch (Exception e) {
			System.out.println("Failed to load logger properties from classpath: " + e.toString());
		}
	}

	public MediaCollectionMain() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				shutdownDatabase();
			}
		}));
		initDependencyInjection();
		startupDatabase();
		startupApplication();
	}

	private void shutdownDatabase() {
		if (databaseManager != null)
			databaseManager.dispose();
	}

	private void startupDatabase() {
		databaseManager = (DatabaseManager) applicationContext.getBean("databaseManager");
		databaseManager.init();
	}

	private void startupApplication() {
		Initializable applicationInitializer = (Initializable) applicationContext.getBean("applicationInitializer");
		applicationInitializer.init();
	}

	private void initDependencyInjection() {
		applicationContext = new ClassPathXmlApplicationContext(
				"com/jeff/booktracker/main/ioc/application-context.xml");
	}

}

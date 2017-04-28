package com.jeff.booktracker.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.jeff.booktracker.db.DatabaseManager;
import com.jeff.booktracker.lifecycle.ui.GUIInitializer;

public class ApplicationInitializer implements Initializable, BeanFactoryAware {

	private DatabaseManager databaseManager;
	private GUIInitializer guiInitializer;
	private BeanFactory beanFactory;

	public ApplicationInitializer(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
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
		guiInitializer = (GUIInitializer) beanFactory.getBean("guiInitializer");
		startupApplication();

	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}

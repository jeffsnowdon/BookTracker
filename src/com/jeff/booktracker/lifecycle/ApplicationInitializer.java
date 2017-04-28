package com.jeff.booktracker.lifecycle;

import com.jeff.booktracker.lifecycle.ui.GUIInitializer;

public class ApplicationInitializer implements Initializable{

	private GUIInitializer guiInitializer;

	public ApplicationInitializer(GUIInitializer guiInitializer) {
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
	}

	@Override
	public void init() {
		startupApplication();

	}

}

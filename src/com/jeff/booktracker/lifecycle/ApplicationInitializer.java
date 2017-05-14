package com.jeff.booktracker.lifecycle;

import com.jeff.booktracker.lifecycle.ui.GUIInitializer;

public class ApplicationInitializer implements Initializable {

	private GUIInitializer guiInitializer;

	public ApplicationInitializer(GUIInitializer guiInitializer) {
		this.guiInitializer = guiInitializer;
	}

	private void startupApplication() {
		initGUI();
	}

	private void initGUI() {
		guiInitializer.init();
	}

	@Override
	public void init() {
		startupApplication();
	}

}

package com.jeff.booktracker.lifecycle.ui;

import javax.swing.JFrame;

import com.jeff.booktracker.lifecycle.Initializable;
import com.jeff.booktracker.ui.BookTrackerMainPanel;

public class GUIInitializer implements Initializable {

	private JFrame mainFrame;
	private BookTrackerMainPanel bookTrackerMainPanel;

	public GUIInitializer(JFrame mainFrame, BookTrackerMainPanel bookTrackerMainPanel) {
		this.mainFrame = mainFrame;
		this.bookTrackerMainPanel = bookTrackerMainPanel;
	}

	@Override
	public void init() {
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setContentPane(bookTrackerMainPanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

}

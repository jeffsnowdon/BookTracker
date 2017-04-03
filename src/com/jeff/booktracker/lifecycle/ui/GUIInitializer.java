package com.jeff.booktracker.lifecycle.ui;

import javax.swing.JFrame;

import com.jeff.booktracker.lifecycle.Initializable;
import com.jeff.booktracker.ui.BookTrackerMainPanel;

public class GUIInitializer implements Initializable {

	private BookTrackerMainPanel bookTrackerMainPanel;

	public GUIInitializer(BookTrackerMainPanel bookTrackerMainPanel) {
		this.bookTrackerMainPanel = bookTrackerMainPanel;
	}

	@Override
	public void init() {
		JFrame frame = new JFrame("Book Tracker");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(bookTrackerMainPanel);
		frame.pack();
		frame.setVisible(true);
	}

}

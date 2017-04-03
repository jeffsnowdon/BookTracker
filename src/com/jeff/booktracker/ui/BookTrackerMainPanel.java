package com.jeff.booktracker.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.jeff.booktracker.bookshelf.ui.BookshelfPanel;

public class BookTrackerMainPanel extends JPanel {

	private BookshelfPanel bookshelfPanel;

	public BookTrackerMainPanel(BookshelfPanel bookshelfPanel) {
		this.bookshelfPanel = bookshelfPanel;
		init();
	}

	private void init() {
		setupContainer();
	}

	private void setupContainer() {
		setLayout(new BorderLayout());
		add(bookshelfPanel, BorderLayout.CENTER);
	}

}

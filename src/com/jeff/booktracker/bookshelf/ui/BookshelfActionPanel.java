package com.jeff.booktracker.bookshelf.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BookshelfActionPanel extends JPanel {

	private JButton addBookButton = new JButton("Add Book");
	private JButton removeBookButton = new JButton("Remove Book");

	public BookshelfActionPanel() {
		init();
	}

	private void init() {
		setupComponents();
		setupContainer();
	}

	private void setupComponents() {
		addBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		removeBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	private void setupContainer() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(addBookButton);
		add(removeBookButton);
	}

}

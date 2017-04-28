package com.jeff.booktracker.bookshelf.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.IBookManager;
import com.jeff.booktracker.bookshelf.ui.add.AddBookDialog;

public class BookshelfActionPanel extends JPanel {

	// UI
	private Frame frame;
	private JButton addBookButton = new JButton("Add Book");
	private JButton removeBookButton = new JButton("Remove Book");
	private AddBookDialog addBookDialog;
	// model
	private IBookManager bookManager;

	public BookshelfActionPanel(Frame frame, AddBookDialog addBookDialog, IBookManager bookManager) {
		this.frame = frame;
		this.addBookDialog = addBookDialog;
		this.bookManager = bookManager;
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
				addBookDialog.setModal(true);
				addBookDialog.pack();
				addBookDialog.setLocationRelativeTo(frame);
				addBookDialog.setVisible(true);
				if (addBookDialog.getOKPressed()) {
					Book book = addBookDialog.produceBook();
					if (book != null)
						bookManager.addOrUpdate(book);
				}
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

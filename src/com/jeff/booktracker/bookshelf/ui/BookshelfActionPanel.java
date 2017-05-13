package com.jeff.booktracker.bookshelf.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.IBookManager;
import com.jeff.booktracker.bookshelf.ui.add.AddBookDialog;
import com.jeff.booktracker.util.IProperty;

public class BookshelfActionPanel extends JPanel {

	// UI
	private Frame frame;
	private JButton addBookButton = new JButton("Add Book");
	private JButton removeBookButton = new JButton("Remove Book");
	private AddBookDialog addBookDialog;
	// model
	private IBookManager bookManager;
	private IProperty<List<Book>> selectedBooksProperty;
	// util
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	public BookshelfActionPanel(Frame frame, AddBookDialog addBookDialog, IBookManager bookManager,
			IProperty<List<Book>> selectedBooksProperty) {
		this.frame = frame;
		this.addBookDialog = addBookDialog;
		this.bookManager = bookManager;
		this.selectedBooksProperty = selectedBooksProperty;
		init();
	}

	private void init() {
		setupComponents();
		setupContainer();
	}

	private void setupComponents() {
		addBookButton.addActionListener(e -> {
			addBookDialog.setModal(true);
			addBookDialog.reset();
			addBookDialog.pack();
			addBookDialog.setLocationRelativeTo(frame);
			addBookDialog.setVisible(true);
			if (addBookDialog.isOKPressed()) {
				Book book = addBookDialog.produceBook();
				if (book != null)
					addOrUpdateBook(book);
			}
		});

		removeBookButton.addActionListener(e -> removeSelectedBooks());
	}

	private void removeSelectedBooks() {
		if (selectedBooksProperty.get().size() == 0) {
			logger.severe("Remove book failed - no book selected.");
			JOptionPane.showMessageDialog(frame, "Remove book failed - no book selected.", "Remove Book Error",
					JOptionPane.ERROR_MESSAGE);
		}
		selectedBooksProperty.get().stream().forEach(BookshelfActionPanel.this::removeBook);
	}

	private void addOrUpdateBook(Book book) {
		try {
			bookManager.addOrUpdate(book);
		} catch (SQLException e) {
			logger.severe("Add book failed - " + book.toString() + " - " + e.toString());
			JOptionPane.showMessageDialog(frame, "Add book \"" + book.getTitle() + "\" failed - database error.",
					"Add Book Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void removeBook(Book book) {
		try {
			bookManager.remove(book);
		} catch (SQLException e) {
			logger.severe("Remove book failed - " + book.toString() + " - " + e.toString());
			JOptionPane.showMessageDialog(frame, "Remove book \"" + book.getTitle() + "\" failed - database error.",
					"Remove Book Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void setupContainer() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(addBookButton);
		add(removeBookButton);
	}

}

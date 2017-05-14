package com.jeff.booktracker.bookshelf.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.IBookManager;
import com.jeff.booktracker.bookshelf.ui.add.AddBookDialog;
import com.jeff.booktracker.util.IProperty;
import com.jeff.booktracker.util.ImageProvider;

public class BookshelfActionPanel extends JPanel {

	// UI
	private Frame frame;
	private JButton addBookButton = new JButton("Add Book");
	private JButton removeBookButton = new JButton("Remove Book");
	private Supplier<AddBookDialog> addBookDialogSupplier;
	// model
	private IBookManager bookManager;
	private IProperty<List<Book>> selectedBooksProperty;
	// util
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	public BookshelfActionPanel(Frame frame, Supplier<AddBookDialog> addBookDialogSupplier, IBookManager bookManager,
			IProperty<List<Book>> selectedBooksProperty) {
		this.frame = frame;
		this.addBookDialogSupplier = addBookDialogSupplier;
		this.bookManager = bookManager;
		this.selectedBooksProperty = selectedBooksProperty;
		init();
	}

	private void init() {
		setupComponents();
		setupContainer();
	}

	private void setupComponents() {
		addBookButton.setIcon(ImageProvider.notebookAdd);
		addBookButton.addActionListener(e -> {
			AddBookDialog addBookDialog = addBookDialogSupplier.get();
			addBookDialog.setModal(true);
			addBookDialog.pack();
			addBookDialog.setLocationRelativeTo(frame);
			addBookDialog.setVisible(true);
			if (addBookDialog.isOKPressed()) {
				Book book = addBookDialog.produceBook();
				if (book != null)
					addBook(book);
			}
		});

		removeBookButton.setIcon(ImageProvider.notebookRemove);
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

	private void addBook(Book book) {
		try {
			if (bookManager.containsBook(book)) {
				logger.severe("A book already exists with that Title and Author.");
				JOptionPane.showMessageDialog(frame, "A book already exists with that Title and Author.",
						"Add Book Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			bookManager.add(book);
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

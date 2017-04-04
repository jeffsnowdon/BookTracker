package com.jeff.booktracker.bookshelf.ui;

import java.awt.BorderLayout;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.ui.table.model.BookshelfTableModel;
import com.jeff.booktracker.bookshelf.ui.table.model.BookshelfTableModelElement;

public class BookshelfPanel extends JPanel {

	// ui
	private JScrollPane tableScrollPane;
	private JTable bookshelfTable;
	private BookshelfActionPanel bookshelfActionPanel;
	// model
	private BookshelfTableModel bookshelfTableModel;

	public BookshelfPanel(BookshelfTableModel bookshelfTableModel, BookshelfActionPanel bookshelfActionPanel) {
		this.bookshelfTableModel = bookshelfTableModel;
		this.bookshelfActionPanel = bookshelfActionPanel;
		init();
	}

	private void init() {
		setupComponents();
		setupContainer();
		for (int i = 0; i < 100; i++)
			bookshelfTableModel
					.addElement(new BookshelfTableModelElement(new Book("Programming", "Jeff", LocalDate.now())));
	}

	private void setupComponents() {
		bookshelfTable = new JTable(bookshelfTableModel);
		tableScrollPane = new JScrollPane(bookshelfTable);
	}

	private void setupContainer() {
		setLayout(new BorderLayout());
		add(tableScrollPane, BorderLayout.CENTER);
		add(bookshelfActionPanel, BorderLayout.SOUTH);
	}

}

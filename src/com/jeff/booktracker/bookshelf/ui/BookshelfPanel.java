package com.jeff.booktracker.bookshelf.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.BookManager;
import com.jeff.booktracker.bookshelf.ui.table.model.BookshelfTableModel;
import com.jeff.booktracker.bookshelf.ui.table.model.BookshelfTableModelElement;

public class BookshelfPanel extends JPanel {

	// ui
	private JScrollPane tableScrollPane;
	private JTable bookshelfTable;
	private BookshelfActionPanel bookshelfActionPanel;
	// model
	private BookshelfTableModel bookshelfTableModel;
	private BookManager bookManager;

	public BookshelfPanel(BookshelfTableModel bookshelfTableModel, BookshelfActionPanel bookshelfActionPanel,
			BookManager bookManager) {
		this.bookshelfTableModel = bookshelfTableModel;
		this.bookshelfActionPanel = bookshelfActionPanel;
		this.bookManager = bookManager;
		init();
	}

	private void init() {
		setupComponents();
		setupContainer();
		updateValues();
		addListeners();
	}

	public void addListeners(){
		bookManager.
	}

	private void updateValues() {
		bookshelfTableModel.removeAllElements();
		for (Book book : bookManager.get()) {
			bookshelfTableModel.addElement(new BookshelfTableModelElement(book));
		}
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

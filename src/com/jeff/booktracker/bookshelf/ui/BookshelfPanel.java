package com.jeff.booktracker.bookshelf.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.BookChangeListener;
import com.jeff.booktracker.bookshelf.model.BookManager;
import com.jeff.booktracker.bookshelf.ui.table.model.BookshelfTableModel;
import com.jeff.booktracker.util.IProperty;

public class BookshelfPanel extends JPanel {

	// ui
	private JScrollPane tableScrollPane;
	private JTable bookshelfTable;
	private BookshelfActionPanel bookshelfActionPanel;
	// model
	private BookshelfTableModel bookshelfTableModel;
	private BookManager bookManager;
	private IProperty<List<Book>> selectedBooksProperty;
	// listener
	private BookChangeListener bookChangeListener = new BookChangeListener() {

		@Override
		public void bookRemoved(Book book) {
			bookshelfTableModel.removeElement(book);
		}

		@Override
		public void bookAdded(Book book) {
			bookshelfTableModel.addElement(book);
		}

		@Override
		public void removedAll() {
			bookshelfTableModel.removeAllElements();
		}
	};

	public BookshelfPanel(BookshelfTableModel bookshelfTableModel, BookshelfActionPanel bookshelfActionPanel,
			BookManager bookManager, IProperty<List<Book>> selectedBooks) {
		this.bookshelfTableModel = bookshelfTableModel;
		this.bookshelfActionPanel = bookshelfActionPanel;
		this.bookManager = bookManager;
		this.selectedBooksProperty = selectedBooks;
		init();
	}

	private void init() {
		setupComponents();
		setupContainer();
		updateValues();
		addListeners();
	}

	private void addListeners() {
		bookManager.addListener(bookChangeListener);
		bookshelfTable.getSelectionModel().addListSelectionListener(e -> {
			if (e.getValueIsAdjusting())
				return;
			List<Book> selectedBooks = new ArrayList<>();
			IntStream.rangeClosed(e.getFirstIndex(), e.getLastIndex())
					.filter(i -> bookshelfTable.getSelectionModel().isSelectedIndex(i))
					.mapToObj(i -> bookshelfTableModel.getBookAtRow(i)).filter(Objects::nonNull)
					.forEach(book -> selectedBooks.add(book));
			selectedBooksProperty.set(selectedBooks);
		});
	}

	private void updateValues() {
		bookshelfTableModel.removeAllElements();
		for (Book book : bookManager.get()) {
			bookshelfTableModel.addElement(book);
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

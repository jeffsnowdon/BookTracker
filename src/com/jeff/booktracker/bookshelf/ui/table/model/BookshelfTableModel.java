package com.jeff.booktracker.bookshelf.ui.table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BookshelfTableModel extends AbstractTableModel {

	private final String[] columnNames = { "Title", "Author", "Date Published" };

	private List<BookshelfTableModelElement> elements = new ArrayList<>();

	@Override
	public int getRowCount() {
		return elements.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return elements.get(rowIndex).getBook().getTitle();
		case 1:
			return elements.get(rowIndex).getBook().getAuthor();
		case 2:
			return elements.get(rowIndex).getBook().getDatePublished();
		default:
			return "N/A";
		}
	}

}

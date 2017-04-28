package com.jeff.booktracker.bookshelf.ui.table.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jeff.booktracker.bookshelf.model.Book;

public class BookshelfTableModel extends AbstractTableModel {

	private final String[] columnNames = { "Title", "Author", "Date Published" };
	private List<Book> elements = new ArrayList<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void addElement(Book element) {
		elements.add(element);
		fireTableDataChanged();
	}

	public void removeElement(Book element) {
		elements.remove(element);
		fireTableDataChanged();
	}

	public void removeAllElements() {
		elements.clear();
		fireTableDataChanged();
	}

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
			return elements.get(rowIndex).getTitle();
		case 1:
			return elements.get(rowIndex).getAuthor();
		case 2:
			return elements.get(rowIndex).getDatePublished().format(formatter);
		default:
			return "N/A";
		}
	}

}

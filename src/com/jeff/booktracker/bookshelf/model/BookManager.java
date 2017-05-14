package com.jeff.booktracker.bookshelf.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookManager implements IBookManager {

	private IBookManager booksPersistor;
	private List<BookChangeListener> eventListeners = new ArrayList<>();

	public BookManager(IBookManager booksPersistor) {
		this.booksPersistor = booksPersistor;
	}

	public void addListener(BookChangeListener bookChangeListener) {
		eventListeners.add(bookChangeListener);
	}

	public void removeListener(BookChangeListener bookChangeListener) {
		eventListeners.remove(bookChangeListener);
	}

	private void fireBookAdded(Book book) {
		eventListeners.stream().forEach(listener -> listener.bookAdded(book));
	}

	@Override
	public Set<Book> get() {
		return booksPersistor.get();
	}

	@Override
	public void remove(Book book) throws SQLException {
		booksPersistor.remove(book);
		fireBookRemoved(book);
	}

	private void fireBookRemoved(Book book) {
		eventListeners.stream().forEach(listener -> listener.bookRemoved(book));
	}

	private void fireRemovedAll() {
		eventListeners.stream().forEach(listener -> listener.removedAll());
	}

	@Override
	public void removeAll() throws SQLException {
		booksPersistor.removeAll();
		fireRemovedAll();
	}

	@Override
	public void add(Book book) throws SQLException {
		booksPersistor.add(book);
		fireBookAdded(book);
	}

	@Override
	public Book getBook(Book book) {
		return booksPersistor.getBook(book);
	}

}

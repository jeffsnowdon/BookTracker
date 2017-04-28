package com.jeff.booktracker.bookshelf.model;

import java.util.List;

public class BookManager implements IBookManager {

	private IBookManager booksPersistor;
	private List<BookChangeListener> eventListeners;

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
	public List<Book> get() {
		return booksPersistor.get();
	}

	@Override
	public void addOrUpdate(Book book) {
		booksPersistor.addOrUpdate(book);
		fireBookAdded(book);
	}

	@Override
	public void remove(Book book) {
		booksPersistor.remove(book);
		fireBookRemoved(book);
	}

	private void fireBookRemoved(Book book) {
		eventListeners.stream().forEach(listener -> listener.bookRemoved(book));
	}

	@Override
	public void removeAll() {
		booksPersistor.removeAll();
	}

}

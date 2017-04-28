package com.jeff.booktracker.bookshelf.model;

import java.util.ArrayList;
import java.util.List;

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
	public List<Book> get() {
		return booksPersistor.get();
	}

	@Override
	public boolean addOrUpdate(Book book) {
		boolean success = booksPersistor.addOrUpdate(book);
		if (success)
			fireBookAdded(book);
		return success;
	}

	@Override
	public boolean remove(Book book) {
		boolean success = booksPersistor.remove(book);
		if (success)
			fireBookRemoved(book);
		return success;
	}

	private void fireBookRemoved(Book book) {
		eventListeners.stream().forEach(listener -> listener.bookRemoved(book));
	}

	private void fireRemovedAll() {
		eventListeners.stream().forEach(listener -> listener.removedAll());
	}

	@Override
	public boolean removeAll() {
		boolean success = booksPersistor.removeAll();
		if (success)
			fireRemovedAll();
		return success;
	}

}

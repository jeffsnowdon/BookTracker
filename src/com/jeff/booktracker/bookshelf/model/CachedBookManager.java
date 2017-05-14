package com.jeff.booktracker.bookshelf.model;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CachedBookManager implements IBookManager {

	// state
	private Set<Book> books = new HashSet<>();
	// model
	private BookManager bookManager;

	public CachedBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
		init();
	}

	public void init() {
		books = bookManager.get();
		addListeners();
	}

	private void addListeners() {
		bookManager.addListener(new BookChangeListener() {

			@Override
			public void removedAll() {
				books.clear();
			}

			@Override
			public void bookRemoved(Book book) {
				books.remove(book);
			}

			@Override
			public void bookAdded(Book book) {
				books.add(book);
			}
		});
	}

	@Override
	public Set<Book> get() {
		return books;
	}

	@Override
	public void remove(Book book) throws SQLException {
		bookManager.remove(book);
	}

	@Override
	public void removeAll() throws SQLException {
		bookManager.removeAll();
	}

	@Override
	public void add(Book book) throws SQLException {
		bookManager.add(book);
	}

	@Override
	public Book getBook(Book book) {
		if (books.contains(book))
			return book;
		return null;
	}

}

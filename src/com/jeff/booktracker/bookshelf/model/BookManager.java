package com.jeff.booktracker.bookshelf.model;

import java.util.List;

public class BookManager implements IBookManager {

	private IBookManager booksPersistor;

	public BookManager(IBookManager booksPersistor) {
		this.booksPersistor = booksPersistor;
	}

	@Override
	public List<Book> get() {
		return booksPersistor.get();
	}

	@Override
	public void addOrUpdate(Book book) {
		booksPersistor.addOrUpdate(book);
	}

	@Override
	public void remove(Book book) {
		booksPersistor.remove(book);
	}

	@Override
	public void removeAll() {
		booksPersistor.removeAll();
	}

}

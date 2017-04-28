package com.jeff.booktracker.bookshelf.model;

import java.util.List;

public interface IBookManager {

	public List<Book> get();

	public void addOrUpdate(Book book);

	public void remove(Book book);

	public void removeAll();

}

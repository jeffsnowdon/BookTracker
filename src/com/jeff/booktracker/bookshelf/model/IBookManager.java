package com.jeff.booktracker.bookshelf.model;

import java.util.List;

public interface IBookManager {

	public List<Book> get();

	public boolean addOrUpdate(Book book);

	public boolean remove(Book book);

	public boolean removeAll();

}

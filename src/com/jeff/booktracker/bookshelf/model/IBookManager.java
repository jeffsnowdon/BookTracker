package com.jeff.booktracker.bookshelf.model;

import java.sql.SQLException;
import java.util.Set;

public interface IBookManager {

	public Set<Book> get();

	public void remove(Book book) throws SQLException;

	public void removeAll() throws SQLException;

	public void add(Book book) throws SQLException;

	public Book getBook(Book book);

}

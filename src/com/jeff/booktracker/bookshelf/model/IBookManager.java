package com.jeff.booktracker.bookshelf.model;

import java.sql.SQLException;
import java.util.List;

public interface IBookManager {

	public List<Book> get();

	public void addOrUpdate(Book book) throws SQLException;

	public void remove(Book book) throws SQLException;

	public void removeAll() throws SQLException;

}

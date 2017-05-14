package com.jeff.booktracker.bookshelf.model;

import java.sql.SQLException;
import java.util.Set;

public interface IBookManager {

	/**
	 * Get all books.
	 * 
	 * @return all books.
	 */
	public Set<Book> get();

	/**
	 * Remove a book.
	 * 
	 * @param book
	 *            - book to remove
	 * @throws SQLException
	 */
	public void remove(Book book) throws SQLException;

	/**
	 * Remove all books.
	 * 
	 * @throws SQLException
	 */
	public void removeAll() throws SQLException;

	/**
	 * Add book.
	 * 
	 * @param book
	 *            - book to add
	 * @throws SQLException
	 */
	public void add(Book book) throws SQLException;

	/**
	 * Check if a book already exists.
	 * 
	 * @param book
	 *            - book to check for existence
	 * @return true if book exists
	 */
	public boolean containsBook(Book book);

}

package com.jeff.booktracker.bookshelf.model;

public interface BookChangeListener {

	/**
	 * A book has been added.
	 * 
	 * @param book
	 *            added book
	 */
	public void bookAdded(Book book);

	/**
	 * A book has been removed.
	 * 
	 * @param book
	 *            removed book
	 */
	public void bookRemoved(Book book);

	/**
	 * All books have been removed.
	 */
	public void removedAll();

}

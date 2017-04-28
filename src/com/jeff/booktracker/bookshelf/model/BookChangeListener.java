package com.jeff.booktracker.bookshelf.model;

public interface BookChangeListener {

	public void bookAdded(Book book);

	public void bookRemoved(Book book);

	public void removedAll();

}

package com.jeff.booktracker.bookshelf.ui.table.model;

import com.jeff.booktracker.bookshelf.model.Book;

public class BookshelfTableModelElement {

	private Book book;

	public Book getBook() {
		return book;
	}

	public BookshelfTableModelElement(Book book) {
		super();
		this.book = book;
	}

}

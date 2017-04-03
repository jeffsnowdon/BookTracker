package com.jeff.booktracker.bookshelf.model;

import java.util.Date;

public class Book {
	private String title;
	private String author;
	private Date datePublished;

	public Book(String title, String author, Date datePublished) {
		super();
		this.title = title;
		this.author = author;
		this.datePublished = datePublished;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

}

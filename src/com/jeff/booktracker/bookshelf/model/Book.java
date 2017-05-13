package com.jeff.booktracker.bookshelf.model;

import java.time.LocalDate;

public class Book {
	private String title;
	private String author;
	private LocalDate datePublished;

	public Book(String title, String author, LocalDate datePublished) {
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

	public LocalDate getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}

	@Override
	public String toString() {
		return "{title: " + title + " author: " + author + " datePublished: " + datePublished.toString() + "}";
	}

}

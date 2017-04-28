package com.jeff.booktracker.bookshelf.db.persistor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.IBookManager;

public class BooksPersistor implements IBookManager {

	public static final String TABLE = "booktracker.books";
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String DATE_PUBLISHED = "datePublished";

	private Connection dbConnection;
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	public BooksPersistor(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	@Override
	public List<Book> get() {
		List<Book> books = new ArrayList<>();
		try {
			Statement statement = dbConnection.createStatement();
			String query = "SELECT * from " + TABLE;
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String title = rs.getString(TITLE);
				String author = rs.getString(AUTHOR);
				Date datePublished = rs.getDate(DATE_PUBLISHED);
				books.add(new Book(title, author, datePublished.toLocalDate()));
			}

		} catch (SQLException e) {
			logger.severe(e.toString());
		}
		return books;
	}

	@Override
	public void addOrUpdate(Book book) {
		remove(book);
		add(book);
	}

	private void add(Book book) {
		try {
			Statement statement = dbConnection.createStatement();

			String title = book.getTitle();
			String author = book.getAuthor();
			LocalDate datePublished = book.getDatePublished();
			String query = "INSERT INTO " + TABLE + "(" + TITLE + "," + AUTHOR + "," + DATE_PUBLISHED + ") VALUES ("
					+ title + "," + author + "," + datePublished + ");";
			statement.executeQuery(query);

		} catch (SQLException e) {
			logger.severe(e.toString());
		}
	}

	@Override
	public void remove(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub

	}

}

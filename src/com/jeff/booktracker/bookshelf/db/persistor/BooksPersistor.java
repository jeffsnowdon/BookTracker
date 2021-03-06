package com.jeff.booktracker.bookshelf.db.persistor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.jeff.booktracker.bookshelf.db.persistor.util.DateConverter;
import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.bookshelf.model.IBookManager;

public class BooksPersistor implements IBookManager {

	public static final String TABLE = "booktracker.books";
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String DATE_PUBLISHED = "datePublished";

	// util
	private Connection dbConnection;
	private DateConverter dateConverter;
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	public BooksPersistor(Connection dbConnection, DateConverter dateConverter) {
		this.dbConnection = dbConnection;
		this.dateConverter = dateConverter;
	}

	@Override
	public Set<Book> get() {
		Set<Book> books = new HashSet<>();
		try {
			Statement statement = dbConnection.createStatement();
			String query = "SELECT * from " + TABLE;
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String title = rs.getString(TITLE);
				String author = rs.getString(AUTHOR);
				LocalDate datePublished = dateConverter.toLocalDate(rs.getDate(DATE_PUBLISHED));
				books.add(new Book(title, author, datePublished));
			}

		} catch (SQLException e) {
			logger.severe(e.toString());
		}
		return books;
	}

	@Override
	public void add(Book book) throws SQLException {
		Statement statement = dbConnection.createStatement();
		String title = book.getTitle();
		String author = book.getAuthor();
		Date datePublished = dateConverter.toSQL(book.getDatePublished());
		String query = "INSERT INTO " + TABLE + " VALUES (" + "'" + title + "'" + "," + "'" + author + "'" + "," + "'"
				+ datePublished.toString() + "'" + ");";
		statement.execute(query);
	}

	@Override
	public void remove(Book book) throws SQLException {
		Statement statement = dbConnection.createStatement();
		String title = book.getTitle();
		String author = book.getAuthor();
		Date datePublished = dateConverter.toSQL(book.getDatePublished());
		String query = "DELETE FROM " + TABLE + " WHERE " + TITLE + "=" + "'" + title + "'" + " AND " + AUTHOR + "="
				+ "'" + author + "'" + " AND " + DATE_PUBLISHED + "=" + "'" + datePublished.toString() + "'" + ";";
		statement.execute(query);
	}

	@Override
	public void removeAll() throws SQLException {
		Statement statement = dbConnection.createStatement();
		String query = "DELETE FROM " + TABLE;
		statement.executeQuery(query);
	}

	@Override
	public boolean containsBook(Book book) {
		Book result = null;
		try {
			Statement statement = dbConnection.createStatement();
			String query = "SELECT * from " + TABLE + " WHERE title='" + book.getTitle() + "' AND author='"
					+ book.getAuthor() + "'";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String title = rs.getString(TITLE);
				String author = rs.getString(AUTHOR);
				LocalDate datePublished = dateConverter.toLocalDate(rs.getDate(DATE_PUBLISHED));
				result = new Book(title, author, datePublished);
			}

		} catch (SQLException e) {
			logger.severe(e.toString());
		}
		return result != null;
	}

}

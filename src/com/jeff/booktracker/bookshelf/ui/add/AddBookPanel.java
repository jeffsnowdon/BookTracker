package com.jeff.booktracker.bookshelf.ui.add;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jeff.booktracker.bookshelf.model.Book;

public class AddBookPanel extends JPanel {

	private JLabel titleLabel = new JLabel("Title:");
	private JLabel authorLabel = new JLabel("Author:");
	private JLabel datePublishedLabel = new JLabel("Date Published:");
	private JTextField titleTextField = new JTextField();
	private JTextField authorTextField = new JTextField();
	// TODO: this needs to be a date picker
	private JTextField datePublishedTextField = new JTextField();

	public AddBookPanel() {
		initGUI();
	}

	public Book produceBook() {
		String title = titleTextField.getText();
		String author = authorTextField.getText();
		LocalDate datePublished = LocalDate.now();
		if (validateInput(title, author, datePublished))
			return new Book(title, author, datePublished);
		return null;
	}

	private boolean validateInput(String title, String author, LocalDate datePublished) {
		if (title == null || title.equals(""))
			return false;
		if (author == null || author.equals(""))
			return false;
		if (datePublished == null)
			return false;
		return true;
	}

	private void initGUI() {
		setupComponents();
		setupContainer();
	}

	private void setupComponents() {
		titleTextField.setPreferredSize(new Dimension(150, 25));
		authorTextField.setPreferredSize(new Dimension(150, 25));
		datePublishedTextField.setPreferredSize(new Dimension(150, 25));
	}

	private void setupContainer() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());

		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.EAST;

		c.gridx = 0;
		c.gridy = 0;
		add(titleLabel, c);
		c.gridx = 1;
		add(titleTextField, c);

		c.gridx = 0;
		c.gridy = 1;
		add(authorLabel, c);
		c.gridx = 1;
		add(authorTextField, c);

		c.gridx = 0;
		c.gridy = 2;
		add(datePublishedLabel, c);
		c.gridx = 1;
		add(datePublishedTextField, c);
	}

}

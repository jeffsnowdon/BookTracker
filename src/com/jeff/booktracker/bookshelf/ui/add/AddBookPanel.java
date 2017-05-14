package com.jeff.booktracker.bookshelf.ui.add;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.jeff.booktracker.bookshelf.model.Book;

public class AddBookPanel extends JPanel {

	private JFrame frame;
	private JLabel titleLabel = new JLabel("Title:");
	private JLabel authorLabel = new JLabel("Author:");
	private JLabel datePublishedLabel = new JLabel("Date Published:");
	private JTextField titleTextField = new JTextField();
	private JTextField authorTextField = new JTextField();
	private JDatePickerImpl datePicker;
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());

	public AddBookPanel(JFrame frame) {
		this.frame = frame;
		initGUI();
	}

	/**
	 * Produce a Book based on the current GUI values.
	 * 
	 * @return Book object. Null on failure.
	 */
	public Book produceBook() {
		String title = titleTextField.getText();
		String author = authorTextField.getText();
		Date selectedDate = (Date) datePicker.getModel().getValue();
		LocalDate datePublished = null;
		if (selectedDate != null)
			datePublished = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if (validateInput(title, author, datePublished))
			return new Book(title, author, datePublished);
		else {
			String errorDescription = "";
			if (title == null || title.equals("")) {
				errorDescription += "\nTitle is required.";
			}
			if (author == null || author.equals("")) {
				errorDescription += "\nAuthor is requried.";
			}
			if (datePublished == null || datePublished.equals("")) {
				errorDescription += "\nDate Published is required.";
			}
			logger.severe("Book input is invalid. " + errorDescription.replace("\n", " "));
			JOptionPane.showMessageDialog(frame, "Invalid input:" + errorDescription, "Add Book Error",
					JOptionPane.ERROR_MESSAGE);
		}
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

	private void setupDatePicker() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setPreferredSize(new Dimension(150, 180));
		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
	}

	private void setupComponents() {
		setupDatePicker();
		titleTextField.setPreferredSize(new Dimension(150, 25));
		authorTextField.setPreferredSize(new Dimension(150, 25));
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
		add(datePicker, c);
	}

}

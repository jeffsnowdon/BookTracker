package com.jeff.booktracker.bookshelf.ui.add;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.jeff.booktracker.bookshelf.model.Book;
import com.jeff.booktracker.util.ImageProvider;

public class AddBookDialog extends JDialog {

	// state
	private boolean okPressed = false;
	// ui
	private AddBookPanel addBookPanel;
	private AddBookActionPanel actionPanel = new AddBookActionPanel();

	public AddBookDialog(Frame frame, AddBookPanel addBookPanel) {
		super(frame, "Add Book");
		this.addBookPanel = addBookPanel;
		init();
	}

	private void init() {
		setIconImage(ImageProvider.notebookAdd.getImage());
		setupContainer();
	}

	/**
	 * Check whether the OK button has been pressed.
	 * 
	 * @return true if OK was pressed
	 */
	public boolean isOKPressed() {
		return okPressed;
	}

	private void setupContainer() {
		setLayout(new BorderLayout());
		add(addBookPanel, BorderLayout.CENTER);
		add(actionPanel, BorderLayout.SOUTH);

		setMinimumSize(new Dimension(275, 175));
		setResizable(false);
	}

	/**
	 * Produce a Book based on the current GUI input.
	 * 
	 * @return Book object. Null on failure.
	 */
	public Book produceBook() {
		return addBookPanel.produceBook();
	}

	private class AddBookActionPanel extends JPanel {

		private JButton okButton = new JButton("OK");
		private JButton cancelButton = new JButton("Cancel");

		public AddBookActionPanel() {
			init();
		}

		private void init() {
			setupComponents();
			setupContainer();
		}

		private void setupComponents() {
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					okPressed = true;
					AddBookDialog.this.setVisible(false);
				}
			});

			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					okPressed = false;
					AddBookDialog.this.setVisible(false);
				}
			});
		}

		private void setupContainer() {
			setLayout(new FlowLayout(FlowLayout.RIGHT));
			add(okButton);
			add(cancelButton);

		}
	}
}

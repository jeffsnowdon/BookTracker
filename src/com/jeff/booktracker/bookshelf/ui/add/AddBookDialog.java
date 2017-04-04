package com.jeff.booktracker.bookshelf.ui.add;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class AddBookDialog extends JDialog {

	private AddBookPanel addBookPanel;
	private AddBookActionPanel actionPanel = new AddBookActionPanel();
	private boolean okPressed = false;

	public AddBookDialog(Frame frame, AddBookPanel addBookPanel) {
		super(frame, "Add Book");
		this.addBookPanel = addBookPanel;
		init();
	}

	private void init() {
		setupContainer();
	}

	public boolean getOKPressed() {
		return okPressed;
	}

	private void setupContainer() {
		setLayout(new BorderLayout());
		add(addBookPanel, BorderLayout.CENTER);
		add(actionPanel, BorderLayout.SOUTH);
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

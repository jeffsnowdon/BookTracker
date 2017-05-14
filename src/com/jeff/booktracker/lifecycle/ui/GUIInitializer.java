package com.jeff.booktracker.lifecycle.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jeff.booktracker.lifecycle.Initializable;
import com.jeff.booktracker.ui.BookTrackerMainPanel;
import com.jeff.booktracker.util.ImageProvider;

public class GUIInitializer implements Initializable {

	private JFrame mainFrame;
	private BookTrackerMainPanel bookTrackerMainPanel;

	public GUIInitializer(JFrame mainFrame, BookTrackerMainPanel bookTrackerMainPanel) {
		this.mainFrame = mainFrame;
		this.bookTrackerMainPanel = bookTrackerMainPanel;
	}

	private JMenuBar produceMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		exitMenuItem.setToolTipText("Exit application");
		exitMenuItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		fileMenu.add(exitMenuItem);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem helpMenuItem = new JMenuItem("About");
		helpMenuItem.setMnemonic(KeyEvent.VK_A);
		helpMenuItem.setToolTipText("Application info");
		helpMenuItem.addActionListener((ActionEvent event) -> {
			JOptionPane.showMessageDialog(mainFrame,
					"Book Tracker\nIcons made by:\nhttp://www.flaticon.com/authors/madebyoliver");
		});
		helpMenu.add(helpMenuItem);

		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

	@Override
	public void init() {
		mainFrame.setJMenuBar(produceMenuBar());
		mainFrame.setIconImage(ImageProvider.notebook.getImage());
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setContentPane(bookTrackerMainPanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

}

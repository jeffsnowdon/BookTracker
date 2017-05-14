package com.jeff.booktracker.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageProvider {

	public static Image notebook = new ImageIcon(ImageProvider.class.getResource("/images/notebook.png")).getImage();
	public static ImageIcon notebookAdd = new ImageIcon(ImageProvider.class.getResource("/images/notebook_add.png"));
	public static ImageIcon notebookRemove = new ImageIcon(
			ImageProvider.class.getResource("/images/notebook_remove.png"));

}

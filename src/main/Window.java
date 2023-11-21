package main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends JFrame {
	
	public Window() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800,800));
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}

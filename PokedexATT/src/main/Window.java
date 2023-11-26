package main;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window extends JFrame {
	
	private BufferedImage windowIcon;
	
	public Window() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800,800));
		this.setIconImage(new ImageIcon("res/IconPokeball.png").getImage());
		this.setTitle("Pokédex");
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}

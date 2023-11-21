package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class Panel extends JPanel {
	
	public Panel() {
		
		this.setPreferredSize(new Dimension(800,800));
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		
		this.setBackground(Color.BLACK); // não funciona, também não funciona no repaint, nem em qualquer lugar :/
	}
	
	public abstract void paintComponent(Graphics blit);
}

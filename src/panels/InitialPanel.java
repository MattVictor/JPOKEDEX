package panels;

import java.awt.Color;
import java.awt.Graphics;

public class InitialPanel extends Panel {

	public InitialPanel() {
		super();
	}

	@Override
	public void paintComponent(Graphics blit) {
		
		blit.setColor(Color.BLACK);
		blit.fillRect(0, 0, 350, 350); // desenhando quadrado preto na tela
	}
}

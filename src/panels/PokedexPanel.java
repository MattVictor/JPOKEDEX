package panels;

import java.awt.Color;
import java.awt.Graphics;

public class PokedexPanel extends Panel {

	public PokedexPanel() {
		super();
	}

	@Override
	public void paintComponent(Graphics blit) {
		
		blit.setColor(Color.GREEN);
		blit.fillRect(0, 0, 350, 350); // desenhando quadrado verde na tela
	}
}

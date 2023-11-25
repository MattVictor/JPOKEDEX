package panels;

import java.awt.Color;
import java.awt.Graphics;

import inputs.Button;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class PokedexPanel {
	
	
	public PokedexPanel() {
		super();
	}

	public void render(Graphics blit) {
		
		blit.setColor(Color.GREEN);
		blit.fillRect(0, 0, 350, 350); // desenhando quadrado verde na tela
	}
	
	public Button[] getButtons() {
		return new Button[] {};
	}
}

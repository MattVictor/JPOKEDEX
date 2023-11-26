package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import inputs.Button;

public class PokemonDetailsPanel {
	
	private BufferedImage pokeballIcon;
	private Button[] buttons;
	
	public PokemonDetailsPanel() {
		
		buttons = new Button[0];
	}
	
	public void render(Graphics blit) {
		
		blit.setColor(Color.GREEN);
		blit.drawRect(0, 0, 100, 100);
		drawUpperBar(blit);
	}
	
	private void drawUpperBar(Graphics blit) {
		
		// desenhando barra vermelha no topo
		blit.setColor(new Color(215,15,45));
		blit.fillRect(0, 5, 800, 100);
		blit.setColor(new Color(250,35,50));
		blit.fillRect(0, 0, 800, 100);
		
		// desenhando texto "National Pokedex"
		blit.setColor(Color.WHITE);
		blit.setFont(new Font("Verdana", Font.PLAIN, 40));
		blit.drawString("National Pokédex", 70, 65);
		
		// desenhando pokebola
		blit.drawImage(pokeballIcon, 720, 20, 60, 60, null);
	}
	
	private void importImages() {
		
		InputStream is = getClass().getResourceAsStream("/IconPokeball.png");
		
		try {pokeballIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}
	
	public Button[] getButtons() {
		return buttons;
	}
}

package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import inputs.Button;
import inputs.MouseInputs;

public class InitialPanel {
	
	private BufferedImage pokeballIcon;
	private BufferedImage highTechDetails;
	
	private Button pokedexButton;
	private Button chartButton;
	private Button teamButton;
	
	
	public InitialPanel() {
		super();
		importImages();
		initClasses();
	}
	
	public void render(Graphics blit) {
		
		drawTechDetails(blit);
		drawPokeballIcon(blit);
		drawCenteredName(blit, "POKÉDEX", 60, 400, 265);
		
		pokedexButton.drawButton(blit);
		chartButton.drawButton(blit);
		teamButton.drawButton(blit);
		
	}
	
	// METODOS AUXILIARES
	
	private void drawTechDetails(Graphics blit) {
		blit.drawImage(highTechDetails, -87, -90, 1900, 1200, null);
	}
	
	private void drawPokeballIcon(Graphics blit) {
		blit.drawImage(pokeballIcon, 320, 85, 105, 105, null);
	}
	
	private void initClasses() {
		
		pokedexButton = new Button(0, 250, 320, 240, 80, "Pokédex", 30);
		chartButton = new Button(1, 250, 430, 240, 80, "Chart", 30);
		teamButton = new Button(2, 250, 540, 240, 80, "Team ", 30);
	}
	
	private void drawCenteredName(Graphics blit, String name, int size, int x, int y) {
		
		blit.setFont(new Font("Verdana", Font.BOLD, size)); // nota: sugerir fonte, acho q só pode do sistema
		blit.setColor(Color.BLACK);
		blit.drawString(name, x - name.length() * (size-6) / 2, y);
	}
	
	private void importImages() {
		
		InputStream is = getClass().getResourceAsStream("/IconPokeball.png");
		
		try {pokeballIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
		
		is = getClass().getResourceAsStream("/HighTechDetails.png");
		
		try {highTechDetails = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}
	
	// GETTERS E SETTERS
	public Button[] getButtons() {
		return new Button[]{pokedexButton, chartButton, teamButton};
	}
}

package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import inputs.Button;

public class InitialPanel {
	
	private BufferedImage pokeballIcon;
	private BufferedImage logo;
	
	private Button pokedexButton;
	private Button chartButton;
	private Button exitButton;
	
	private ArrayList<Button> buttons;
	
	public InitialPanel() {
		super();
		importImages();
		initClasses();
	}
	
	public void render(Graphics blit) {
		
		drawPokeballIcon(blit);
		drawCenteredName(blit);
		
		pokedexButton.drawButton(blit);
		chartButton.drawButton(blit);
		exitButton.drawButton(blit);
	}
	
	// METODOS AUXILIARES
	
	private void drawPokeballIcon(Graphics blit) {
		blit.drawImage(pokeballIcon, 320, 85, 105, 105, null);
	}
	
	private void initClasses() {
		
		pokedexButton = new Button(0, 250, 320, 240, 80, "Pokédex", 30);
		chartButton = new Button(1, 250, 430, 240, 80, "Chart", 30);
		exitButton = new Button(2, 250, 540, 240, 80, "Exit", 30);
		
		buttons = new ArrayList<Button>();
		buttons.add(pokedexButton);
		buttons.add(chartButton);
		buttons.add(exitButton);
	}
	
	private void drawCenteredName(Graphics blit) {
		
		blit.drawImage(logo, 210, 190, 322, 103, null);
	}
	
	private void importImages() {
		
		InputStream is = getClass().getResourceAsStream("/IconPokeball.png");
		
		try {pokeballIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
		
		is = getClass().getResourceAsStream("/JPokedex.png");
		
		try {logo = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}
	
	// GETTERS E SETTERS
	public ArrayList<Button> getButtons() {
		return buttons;
	}
}

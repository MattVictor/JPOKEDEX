package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import inputs.Button;
import inputs.Pokemon;
import inputs.PokemonButton;

public class PokedexPanel {
	
	private BufferedImage pokeballIcon;
	private BufferedImage backArrowIcon;
	private Button backToMenuButton;
	
	private ArrayList<PokemonButton> pokemonButtons;
	private ArrayList<Button> UIButtons;
	
	public PokedexPanel() {
		super();
		importImages();
		PokemonButton.importTypeImages();
		Pokemon.initiatePokemons();
		initClasses();
	}

	
	public void render(Graphics blit) {
		
		drawUpperBar(blit);
		drawUIButtons(blit);
		drawPokemonButtons(blit);
	}
	
	
	// METODOS AUXILIARES
	
	private void initClasses() {
		
		UIButtons = new ArrayList<Button>();
		pokemonButtons = new ArrayList<PokemonButton>();
		backToMenuButton = new Button(0,10,30,40,40,backArrowIcon);
		createPokemonButtons();
		UIButtons.add(backToMenuButton);
	}
	
	private void createPokemonButtons() {
		
		for(int i=0; i< Pokemon.getPokemons().size(); i++) {
			
			pokemonButtons.add(
					new PokemonButton(i+1,15, 215 * i + 115, Pokemon.getPokemons().get(i))
			);
		}
	}
	
	private void drawUIButtons(Graphics blit) {
		
		backToMenuButton.drawButton(blit);
	}
	
	private void drawPokemonButtons(Graphics blit) {
		
		for (PokemonButton pokemonButton : pokemonButtons) {
			pokemonButton.drawPokemonButton(blit);
		}
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
		
		is = getClass().getResourceAsStream("/backArrowIcon.png");
		
		try {backArrowIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}
	
	// GETTERS E SETTERS
	public Button[] getButtons() {
		
		Button[] allButtons = new Button[UIButtons.size() + pokemonButtons.size()];
		System.arraycopy(UIButtons.toArray(), 0, allButtons, 0, UIButtons.size());
		System.arraycopy(pokemonButtons.toArray(), 0, allButtons, UIButtons.size(), pokemonButtons.size());
		
		return allButtons;
	}
}

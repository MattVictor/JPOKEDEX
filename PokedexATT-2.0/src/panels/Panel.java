package panels;

import static main.PanelController.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.MouseInputs;
import inputs.PokemonButton;
import main.PanelController;

public class Panel extends JPanel {
	
	private InitialPanel initialPanel;
	private PokedexPanel pokedexPanel;
	private PokemonDetailsPanel pokemonDetailsPanel;
	
	private BufferedImage pokemonBG;
	private double[] pokeBGPos;
	
	private MouseInputs mouseInputs;
	
	public Panel() {
		
		setPanelSize();
		importImages();
		initClasses();
		this.addMouseListener(mouseInputs);
		this.addMouseMotionListener(mouseInputs);
		this.addMouseWheelListener(mouseInputs);
	}
	
	public void paintComponent(Graphics blit) {
		super.paintComponent(blit);
		drawPokemonBG(blit);
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			
			setBackground(new Color(250,200,240));
			initialPanel.render(blit);
		}
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			
			setBackground(new Color(225,225,225));
			pokedexPanel.render(blit);
		}
		else if (PanelController.currentPanel == POKEMON_DETAILS_PANEL) {
			
			setBackground(new Color(240,240,240));
			pokemonDetailsPanel.render(blit);
		}
	}
	
	public void updateMouseListener() {
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			
			mouseInputs.setButtons(initialPanel.getButtons());
			mouseInputs.setPokemonButtons(new ArrayList<PokemonButton>());
		}
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			
			mouseInputs.setButtons(pokedexPanel.getButtons());
			mouseInputs.setPokemonButtons(pokedexPanel.getPokemonButtons());
		}
		else if (PanelController.currentPanel == POKEMON_DETAILS_PANEL) {
			
			mouseInputs.setButtons(pokemonDetailsPanel.getButtons());
			mouseInputs.setPokemonButtons(pokemonDetailsPanel.getPokemonButtons());
		}
	}
	
	private void drawPokemonBG(Graphics blit) {
		
		pokeBGPos[0] -= 0.5;
		pokeBGPos[1] -= 0.1;
		
		blit.drawImage(pokemonBG, (int)pokeBGPos[0], (int)pokeBGPos[1], 3782, 1757, null);
		
		if (pokeBGPos[0] < -2058) {
			pokeBGPos[0] = 0;
		}
		if (pokeBGPos[1] < -956) {
			pokeBGPos[1] = 0;
		}
	}
	
	private void initClasses() {
		
		initialPanel = new InitialPanel();
		pokedexPanel = new PokedexPanel();
		mouseInputs = new MouseInputs(this);
		
		pokeBGPos = new double[]{-200,-200};
	}
	
	public void initiatePokemonDetails() {
		pokemonDetailsPanel = new PokemonDetailsPanel();
	}
	
	private void setPanelSize() {
		
		Dimension size = new Dimension(800,800);
		this.setSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
	}
	
	private void importImages() {
		
		InputStream is = getClass().getResourceAsStream("/pokemonsBg.png");
		
		try {pokemonBG = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}

	// GETTERS E SETTERS
	public PokemonDetailsPanel getPokemonDetailsPanel() {
		return pokemonDetailsPanel;
	}
}

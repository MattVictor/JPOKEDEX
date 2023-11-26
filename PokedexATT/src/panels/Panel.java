package panels;

import static main.PanelController.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.MouseInputs;
import main.PanelController;

public class Panel extends JPanel {
	
	private InitialPanel initialPanel;
	private PokedexPanel pokedexPanel;
	private PokemonDetailsPanel pokemonDetailsPanel;
	
	private MouseInputs mouseInputs;
	
	public Panel() {
		
		initialPanel = new InitialPanel();
		pokedexPanel = new PokedexPanel();
		mouseInputs = new MouseInputs();
		pokemonDetailsPanel = new PokemonDetailsPanel();
		
		this.addMouseListener(mouseInputs);
		this.addMouseMotionListener(mouseInputs);
		this.setPanelSize();
	}
	
	public void paintComponent(Graphics blit) {
		super.paintComponent(blit);
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			
			setBackground(new Color(250,35,50));
			initialPanel.render(blit);
		}
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			
			setBackground(Color.WHITE);
			pokedexPanel.render(blit);
		}
		else if (PanelController.currentPanel == POKEMON_DETAILS_PANEL) {
			
			setBackground(Color.WHITE);
			pokemonDetailsPanel.render(blit);
		}
	}
	
	public void updateMouseListener() {
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			mouseInputs.setButtons(initialPanel.getButtons());
		}
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			mouseInputs.setButtons(pokedexPanel.getButtons());
		}
		else if (PanelController.currentPanel == POKEMON_DETAILS_PANEL) {
			mouseInputs.setButtons(pokemonDetailsPanel.getButtons());
		}
	}
	
	private void setPanelSize() {
		
		Dimension size = new Dimension(800,800);
		this.setSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
	}
}

package panels;

import static main.PanelController.INITIAL_PANEL;
import static main.PanelController.POKEDEX_PANEL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.Button;
import inputs.MouseInputs;
import main.PanelController;

public class Panel extends JPanel {
	
	private InitialPanel initialPanel;
	private PokedexPanel pokedexPanel;
	
	private MouseInputs mouseInputs;
	
	public Panel() {
		
		initialPanel = new InitialPanel();
		pokedexPanel = new PokedexPanel();
		mouseInputs = new MouseInputs();
		
		this.addMouseListener(mouseInputs);
		this.addMouseMotionListener(mouseInputs);
		this.setPreferredSize(new Dimension(800,800));
	}
	
	public void paintComponent(Graphics blit) {
		super.paintComponent(blit);
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			
			setBackground(new Color(250,35,50));
			initialPanel.render(blit);
		}
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			
			setBackground(new Color(250,35,50));
			pokedexPanel.render(blit);
		}
	}
	
	public void updateMouseListener() {
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			mouseInputs.setButtons(initialPanel.getButtons());
		}
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			mouseInputs.setButtons(pokedexPanel.getButtons());
		}
	}
}

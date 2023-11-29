package inputs;

import static main.PanelController.*;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Map;
import main.PanelController;
import panels.ChartPanel;
import panels.Panel;
import panels.PokemonDetailsPanel;

public class MouseInputs implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private Panel mainPanel;
	private boolean firstClickOnDetails;
	private ArrayList<Button> buttons;
	private ArrayList<PokemonButton> pokemonButtons;
	
	public MouseInputs(Panel mainPanel) {
		this.mainPanel = mainPanel;
		this.firstClickOnDetails = true;
		this.buttons = new ArrayList<Button>();
		this.pokemonButtons = new ArrayList<PokemonButton>();
	}

	// alterar conjunto de botoes na tela atual que serao implementados
	public void setButtons(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}
	
	// alterar botoes de pokemon na tela atual que serao implementados
	public void setPokemonButtons(ArrayList<PokemonButton> pokemonButtons) {
		this.pokemonButtons = pokemonButtons;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		for (Button button : buttons) {
			button.selectIfMouseOver(x, y);
		}
		
		for (PokemonButton pButton : pokemonButtons) {
			pButton.selectIfMouseOver(x, y);
		}
		
		if (PanelController.currentPanel == CHART_PANEL) {
			checkChartPanelHitboxes(x,y);
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if (PanelController.currentPanel == POKEDEX_PANEL) {
			int scrollAmount = PokemonButton.getScrollAmount();
			
			// desliza os pokebuttons caso use o scroll do mouse
			if (e.getWheelRotation() > 0) {
				PokemonButton.setScrollAmount(scrollAmount - 40);
			}
			else if (e.getWheelRotation() < 0 && scrollAmount <= 0) {
				PokemonButton.setScrollAmount(PokemonButton.getScrollAmount() + 40);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + " " + y); //printar posicao do clique
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			
			// iterando sobre os botoes comuns do painel inicial
			Button[] currentButtons = new Button[buttons.toArray().length];
			System.arraycopy(buttons.toArray(), 0, currentButtons, 0, buttons.toArray().length);
			
			for (Button button : currentButtons) {
				if (button.mouseClicked(x, y)) {
					
					// caso tenha sido clicado, implementar sua funcao
					implementInitialPanelButtons(button);
				}
			}
		}
		
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			
			PokemonButton[] currentPokeButtons = new PokemonButton[pokemonButtons.toArray().length];
			System.arraycopy(pokemonButtons.toArray(), 0, currentPokeButtons, 0, pokemonButtons.toArray().length);
			
			// iterando sobre os botoes de pokemon da pokedex
			for (PokemonButton pokemonButton : currentPokeButtons) {
				if (pokemonButton.mouseClicked(x, y)) {
					
					// implementando funcao do botao de pokemon
					implementPokedexPokemonButtons(pokemonButton);
				}
			}
			
			// iterando sobre os botoes normais da pokedex
			Button[] currentButtons = new Button[buttons.toArray().length];
			System.arraycopy(buttons.toArray(), 0, currentButtons, 0, buttons.toArray().length);
			
			for (Button button : currentButtons) {
				if (button.mouseClicked(x, y)) {
					
					// implementando suas funcoes
					implementPokedexPanelButtons(button);
				}
			}
		}
		
		else if (PanelController.currentPanel == POKEMON_DETAILS_PANEL) {
			
			PokemonButton[] currentPokeButtons = new PokemonButton[pokemonButtons.toArray().length];
			System.arraycopy(pokemonButtons.toArray(), 0, currentPokeButtons, 0, pokemonButtons.toArray().length);
			
			// iterando sobre os botoes de pokemon do painel de detalhes
			for (PokemonButton pokemonButton : currentPokeButtons) {
				if (pokemonButton.mouseClicked(x, y)) {
					
					// implementando botoes de pokemon
					implementPokemonDetailsPokemonButtons(pokemonButton);
				}
			}
			
			Button[] currentButtons = new Button[buttons.toArray().length];
			System.arraycopy(buttons.toArray(), 0, currentButtons, 0, buttons.toArray().length);
			
			// iterando sobre os outros botoes
			for (Button button : currentButtons) {
				if (button.mouseClicked(x, y)) {
					
					// implementando os botoes comuns
					implementPokemonDetailsPanelButtons(button);
				}
			}
		}
		
		else if (PanelController.currentPanel == CHART_PANEL) {
			
			Button[] currentButtons = new Button[buttons.toArray().length];
			System.arraycopy(buttons.toArray(), 0, currentButtons, 0, buttons.toArray().length);
			
			// iterando sobre os botoes da chart
			for (Button button : currentButtons) {
				if (button.mouseClicked(x, y)) {
					
					// implementando os botoes comuns
					implementChartPanelButtons(button);
				}
			}
		}
	}
	
	// METODOS AUXILIARES
	
	private void implementInitialPanelButtons(Button button) {
		
		switch (button.getID()) {
		case 0:
			PanelController.currentPanel = POKEDEX_PANEL;
			PokemonButton.setScrollAmount(0);
			break;
		case 1:
			PanelController.currentPanel = CHART_PANEL;
			break;
		case 2:
			System.exit(0);
			break;
		}
	}
	
	private void implementPokedexPanelButtons(Button button) {
		
		switch (button.getID()) {
		case 0:
			PanelController.currentPanel = INITIAL_PANEL;
			break;
		}
	}
	
	private void implementPokedexPokemonButtons(PokemonButton pokemonButton) {
		
		if(firstClickOnDetails) {
			mainPanel.initiatePokemonDetails();
			this.firstClickOnDetails = false;
		}
		
		PanelController.currentPanel = POKEMON_DETAILS_PANEL;
		PokemonButton.setScrollAmount(0);
		this.mainPanel.getPokemonDetailsPanel().updateInformations(POKEDEX_PANEL, pokemonButton.getPokemon());
	}
	
	private void implementPokemonDetailsPanelButtons(Button button) {
		
		switch (button.getID()) {
		case 0:
			PanelController.currentPanel = POKEDEX_PANEL;
		}
	}
	
	private void implementPokemonDetailsPokemonButtons(PokemonButton pokeButton) {
		
		this.mainPanel.getPokemonDetailsPanel().updateInformations(POKEMON_DETAILS_PANEL, pokeButton.getPokemon());
	}
	
	private void implementChartPanelButtons(Button button) {
		
		switch (button.getID()) {
		case 0:
			PanelController.currentPanel = INITIAL_PANEL;
			break;
		case 1:
			ChartPanel.setChartMode(true);
			break;
		case 2:
			ChartPanel.setChartMode(false);
			break;
		}
	}
	
	private void checkChartPanelHitboxes(int x, int y) {
		
		Map<String,Rectangle> hitboxes = ChartPanel.getHitboxes();
		
		for(Map.Entry<String,Rectangle> entry : hitboxes.entrySet()) {
			
			if (entry.getValue().contains(x, y)) {
				ChartPanel.setActualChart(entry.getKey());
			}
		}
	}
	
	// METODOS NAO UTILIZADOS DAS INTERFACES DO MOUSE
	
	@Override public void mouseDragged(MouseEvent e) {}
	
	@Override public void mousePressed(MouseEvent e) {}

	@Override public void mouseReleased(MouseEvent e) {}

	@Override public void mouseEntered(MouseEvent e) {}

	@Override public void mouseExited(MouseEvent e) {}

	
}

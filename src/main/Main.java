package main;

import static main.PanelController.*;
import inputs.KeyboardInputs;
import panels.InitialPanel;
import panels.Panel;
import panels.PokedexPanel;

public class Main {
	
	private static Window window;
	private static Panel initialPanel;
	private static Panel dexPanel;
	private static Panel currentPanel;
	private static KeyboardInputs keyboardInputs;
	
	
	
	public static void main(String[] args) {
		
		Main.initClasses();
		
		while (true) {
			Main.ChangePanel();
			Main.repaintCurrentPanel();
		}
	}
	
	
	
	// METODOS AUXILIARES
	
	public static void initClasses() {
		
		// inicia todas as classes principais e paineis da dex.
		
		window = new Window();
		initialPanel = new InitialPanel();
		dexPanel = new PokedexPanel();
		
		keyboardInputs = new KeyboardInputs();
		window.addKeyListener(keyboardInputs);
		window.requestFocus();
	}
	
	
	public static void repaintCurrentPanel()  {
		
		// redesenha e deixa visivel o novo painel.
		
		currentPanel.repaint();
		window.setVisible(true);
	}
	
	public static void ChangePanel() {
		
		// muda o painel atual baseado na constante guardada no controller.
		
		if (PanelController.currentPanel == INITIAL_PANEL) {
			currentPanel = initialPanel;
		}
		
		else if (PanelController.currentPanel == POKEDEX_PANEL) {
			currentPanel = dexPanel;
		}
		
		// adicionando na janela
		window.add(currentPanel);
	}
	
	
	// GETTERS E SETTERS
	public static Panel getCurrentPanel() {
		return currentPanel;
	}
}

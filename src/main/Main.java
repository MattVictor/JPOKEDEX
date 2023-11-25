package main;

import inputs.KeyboardInputs;
import panels.InitialPanel;
import panels.Panel;

public class Main {
	
	private static Window window;
	private static Panel currentPanel;
	private static KeyboardInputs keyboardInputs;
	
	
	public static void main(String[] args) {
		
		Main.initClasses();
		
		while (true) {
			Main.repaintCurrentPanel();
		}
	}
	
	
	// METODOS AUXILIARES
	
	public static void initClasses() {
		
		// inicia todas as classes principais e paineis da dex.
		
		window = new Window();
		currentPanel = new Panel();
		keyboardInputs = new KeyboardInputs();
		
		window.add(currentPanel);
		window.addKeyListener(keyboardInputs);
		window.requestFocus();
	}
	
	
	public static void repaintCurrentPanel()  {
		
		// redesenha e deixa visivel o novo painel.
		currentPanel.repaint();
		currentPanel.updateMouseListener();
		window.setVisible(true);
	}
	
	
	// GETTERS E SETTERS
	public static Panel getCurrentPanel() {
		return currentPanel;
	}
}

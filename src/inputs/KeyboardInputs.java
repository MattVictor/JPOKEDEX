package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.PanelController;
import static main.PanelController.*;

public class KeyboardInputs implements KeyListener {
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// mudando a variavel que controla o menu atual baseado na tecla
		
		if (e.getKeyCode() == KeyEvent.VK_M) {
			PanelController.currentPanel = POKEDEX_PANEL;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_N) {
			PanelController.currentPanel = INITIAL_PANEL;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

package inputs;

import static main.PanelController.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.PanelController;

public class MouseInputs implements MouseListener, MouseMotionListener {
	
	private Button[] buttons;
	
	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		for (Button button : buttons) {
			button.selectIfMouseOver(x, y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		
		for (Button button : buttons) {
			if (button.mouseClicked(x, y)) {
				
				if (PanelController.currentPanel == INITIAL_PANEL)
				
				switch (button.getID()) {
				case 0:
					PanelController.currentPanel = POKEDEX_PANEL;
					break;
				case 1:
					PanelController.currentPanel = CHART_PANEL;
					break;
				case 2:
					PanelController.currentPanel = TEAM_PANEL;
					break;
				}
				
				else if (PanelController.currentPanel == POKEDEX_PANEL) {
				
				switch (button.getID()) {
				case 0:
					PanelController.currentPanel = INITIAL_PANEL;
					break;
				case 1:
					PanelController.currentPanel = POKEMON_DETAILS_PANEL;
					break;
				case 2:
					PanelController.currentPanel = POKEMON_DETAILS_PANEL;
					break;
				}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

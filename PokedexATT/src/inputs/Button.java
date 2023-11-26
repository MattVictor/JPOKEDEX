package inputs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Button {
	
	private int x, y, width, height;
	private Rectangle hitbox;
	private BufferedImage icon;
	private String text;
	private final int ID;
	
	private int iconX, iconY;
	private int iconWidth, iconHeight;
	private double textX, textY;
	private int textSize;
	
	private boolean isMouseOver;
	
	
	public Button(int ID, int x, int y, int width, int height, BufferedImage icon) {
		
		this.ID = ID;
		this.icon = icon;
		this.text = null;
		initiateDimensions(x,y,width,height);
	}
	
	public Button(int ID, int x, int y, int width, int height, String text, int textSize) {
		
		this.ID = ID;
		this.icon = null;
		this.text = text;
		this.textSize = textSize;
		initiateDimensions(x,y,width,height);
	}
	
	public Button(int ID, int x, int y, int width, int height, BufferedImage icon, String text, int textSize) {
		
		this.ID = ID;
		this.icon = icon;
		this.text = text;
		this.textSize = textSize;
		initiateDimensions(x,y,width,height);
	}
	
	
	private void initiateDimensions(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.iconX = x + 6;
		this.iconY = y + 6;
		this.isMouseOver = false;
		this.hitbox = new Rectangle(x,y,width,height);
	}
	
	public void setIconPositionRelativeToButton(int x, int y) {
		
		this.iconX = x;
		this.iconY = y;
	}
	
	public void drawButton(Graphics blit) {
		
		// desenhar borda cinza
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(x, y, width, height, 4, 4);
		
		drawButtonColor(blit);
		drawButtonIcon(blit);
		drawButtonText(blit);
	}
	
	private void drawButtonColor(Graphics blit) {
		
		if (this.isMouseOver) {
			// desenhar botao cinza meio escuro
			blit.setColor(new Color(90,90,90));
			blit.fillRoundRect(x+3, y+3, width-6, height-6, 4, 4);
		}
		else {
			// desenhar botao cinza claro
			blit.setColor(new Color(160,160,160));
			blit.fillRoundRect(x+3, y+3, width-6, height-6, 4, 4);
		}
		
	}
	
	private void drawButtonIcon(Graphics blit) {
		
		if (this.icon != null) {
			
			iconWidth = icon.getWidth() * (height - 12) / icon.getHeight();
			iconHeight = height - 12;
			
			blit.drawImage(icon, iconX, iconY, iconWidth, iconHeight, null);
		}
	}
	
	private void drawButtonText(Graphics blit) {
		
		if (this.text != null) {
			
			blit.setColor(Color.BLACK);
			if (this.icon != null) { // se tem icone no botao
				
				blit.setFont(new Font("Verdana", Font.PLAIN, textSize));
				
				textX = iconX + iconWidth + 3;
				textY = y + height/2 + textSize/2.6;
			}
			else { // se nao tem icone no botao
				
				blit.setFont(new Font("Verdana", Font.PLAIN, textSize));
				
				textX = x + width/2 - text.length()*(textSize/1.65)/2;   // centro do botão
				textY = y + height/2 + textSize/2.6;
			}
			
			blit.drawString(text, (int)textX, (int)textY);
		}
	}
	
	public void selectIfMouseOver(int x, int y) {
		
		if (this.hitbox.contains(x, y)) {
			this.isMouseOver = true;
		}
		else this.isMouseOver = false;
	}
	
	public boolean mouseClicked(int x, int y) {
		
		if (this.hitbox.contains(x, y)) {
			this.isMouseOver = false;
			return true;
		}
		else return false;
	}

	// GETTERS E SETTERS
	public int getID() {
		return ID;
	}
}

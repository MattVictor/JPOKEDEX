package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MultiContentLabel {
	
	private int x,y,width,height,textSize;
	private String title;
	private String[] contents;
	
	
	public MultiContentLabel(int x, int y, int width, int height, int textSize, String title) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textSize = textSize;
		this.title = title;
	}
	
	public void render(Graphics blit) {
		
		drawBody(blit);
		drawTexts(blit);
	}
	
	private void drawBody(Graphics blit) {
		
		// desenhando borda cinza
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(x-3, y-4, width+6, height+8, 15, 15);
		
		// desenhando fundo cinza claro
		blit.setColor(new Color(160,160,160));
		blit.fillRoundRect(x, y, width, height, 15, 15);
		
		// desenhando topo vermelho
		blit.setColor(new Color(215,5,35));
		blit.fillRoundRect(x, y, width, textSize*2, 15, 15);
		blit.fillRect(x, y+textSize, width, textSize);
		
		// desenhando linha preta
		blit.setColor(Color.BLACK);
		blit.drawLine(x, y+textSize*2, x+width, y+textSize*2);
		blit.fillRect(x, y+textSize*2-3, width, 3);
	}
	
	private void drawTexts(Graphics blit) {
		
		blit.setColor(Color.BLACK);
		blit.setFont(new Font("Verdana", Font.BOLD, textSize));
		blit.drawString(title, x + 10, y + textSize + 6);
		
		blit.setFont(new Font("Verdana", Font.PLAIN, textSize));
		for (int i=0; i<contents.length; i++) {
			blit.drawString(contents[i], x + 10, y + textSize*3+3 + i*(textSize+8));
		}
	}
	
	public void updateInformation(String... strings) {
		this.contents = strings;
	}
}

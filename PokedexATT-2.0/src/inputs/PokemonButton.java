package inputs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import panels.PokedexPanel;
import pokemon.Pokemon;

public class PokemonButton extends Button {
	
	private Pokemon pokemon;
	private BufferedImage icon;
	private static int scrollAmount;
	private int x, y, width, height;
	private final int initialPosition;
	
	private static Map<String,BufferedImage> types;
	
	
	public PokemonButton(int x, int y, int width, int height, Pokemon pokemon) {
		super(-1, x, y, width, height, pokemon.getSPRITE());
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.initialPosition = y;
		this.icon = pokemon.getSPRITE();
		this.pokemon = pokemon;
		this.hitbox = new Rectangle(x,y,width,height);
	}
	
	public void drawPokemonButton(Graphics blit) {
		updateButtonPosition();
		
		drawBody(blit);
		drawPokemonIcon(blit);
		if (width == 770) {
			drawPokemonName(blit, 40);
			drawTypesIcons(blit);
		}
	}
	
	public void updateButtonPosition() {
		
		this.y = initialPosition + scrollAmount;
		this.hitbox.setLocation(x, y);
	}
	
	private void drawTypesIcons(Graphics blit) {
		
		String[] pokemonTypes = pokemon.getTIPO();
		int lastIconSize = 0;
		
		// percorrendo dicionario com os nomes dos tipos e seus PNGs
		for(Map.Entry<String,BufferedImage> typesEntry : types.entrySet()) {
			
			// para cada tipo do pokemon, checa se ele eh o atual no map
			for(int i=0; i< pokemonTypes.length; i++) {
				
				if (Objects.equals(pokemonTypes[i], typesEntry.getKey())) {
					
					// desenha os tipos, baseado no valor guardado no mapa correspondente aos tipos do poke
					BufferedImage typeIcon = typesEntry.getValue();
					blit.drawImage(typeIcon, x + 190 + lastIconSize, y + 130, typeIcon.getWidth()/2, 41, null);
					
					lastIconSize = typeIcon.getWidth()/2 - 10;
				}
			}
		}
	}
	
	private void drawPokemonName(Graphics blit, int textSize) {
		
		String name = this.pokemon.getNOME();
		String dexNumber = pokemon.getDEXNUMBER();
		
		blit.setColor(Color.BLACK);
		// desenhando nome do pokemon
		blit.setFont(new Font("Verdana", Font.PLAIN, textSize));
		blit.drawString(name, x + 200, y + 25 + textSize);
		
		// desenhando o número da dex do pokemon
		blit.setFont(new Font("Verdana", Font.PLAIN, textSize - 15));
		blit.drawString(dexNumber, x + 200, y + textSize*2 + 17);
	}
	
	private void drawPokemonIcon(Graphics blit) {
		
		int pokeIconX, pokeIconY, iconHeight, iconWidth, offset;
		
		if (width == 770) {
			iconHeight = 180;
			iconWidth = 180;
			pokeIconX = x + 10;
			pokeIconY = y + 10;
			offset = 15;
		}
		else {
			iconHeight = height;
			iconWidth = icon.getWidth() * height / icon.getHeight();
			pokeIconX = x;
			pokeIconY = y;
			offset = 0;
		}
		
		if (width == 770) {
		// desenhar borda cinza do pokemon
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(pokeIconX+offset, pokeIconY+offset, iconWidth-offset*2, iconHeight-offset*2, 4, 4);
		
		// desenhar corpo cinza claro do pokemon
		blit.setColor(Color.WHITE);
		int boxWidth = iconHeight - (offset+5)*2;
		blit.fillRoundRect(pokeIconX+offset+5, pokeIconY+offset+5, boxWidth, boxWidth, 4, 4);
		}
		
		// desenhando imagem do pokemon
		blit.drawImage(icon, pokeIconX, pokeIconY, iconWidth, iconHeight, null);
	}
	
	private void drawBody(Graphics blit) {
		
		// desenhar borda cinza
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(x, y, width, height, 4, 4);
		
		if (this.isMouseOver) {
			
			// desenhar corpo cinza claro
			blit.setColor(new Color(140,140,140));
			blit.fillRoundRect(x+3, y+3, width-6, height-6, 4, 4);
		}
		else {
			
			// desenhar corpo cinza medio caso o mouse esteja sobre
			blit.setColor(new Color(160,160,160));
			blit.fillRoundRect(x+3, y+3, width-6, height-6, 4, 4);
		}
	}
	
	public static void importTypeImages() {
		
		String is;
		BufferedImage typeImage = null;
		types = new LinkedHashMap<String, BufferedImage>();
		
		String[] typeNames = {
			"psychic","bug","dark","dragon","electric",
			"fairy","fighting","fire","flying",
			"ghost","grass","ground","ice",
			"normal","poison","rock",
			"steel","water"
		};
		
		for (int i=0; i<18; i++) {
			is = "res/PokemonTypes/" + typeNames[i] + ".png";
			
			try {typeImage = ImageIO.read(new File(is));}
			catch (Exception e) {e.printStackTrace();}
			
			types.put(typeNames[i], typeImage);
		}
	}
	
	// GETTERS E SETTERS
	public static int getScrollAmount() {
		return scrollAmount;
	}

	public static void setScrollAmount(int scrollAmount) {
		PokemonButton.scrollAmount = scrollAmount;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public static Map<String, BufferedImage> getTypes() {
		return types;
	}
}

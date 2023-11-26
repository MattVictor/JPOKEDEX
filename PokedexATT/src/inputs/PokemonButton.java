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

public class PokemonButton extends Button {
	
	private Pokemon pokemon;
	private Rectangle hitbox;
	private BufferedImage icon;
	private int x, y, width, height, ID;
	
	private static Map<String,BufferedImage> types;
	
	
	public PokemonButton(int ID, int x, int y, Pokemon pokemon) {
		super(ID, x, y, 770, 200, pokemon.getIcon());
		
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.width = 770;
		this.height = 200;
		this.icon = pokemon.getIcon();
		this.pokemon = pokemon;
		this.hitbox = new Rectangle(x,y,width,height);
	}
	
	public void drawPokemonButton(Graphics blit) {
		
		drawBody(blit);
		drawPokemonIcon(blit);
		drawPokemonName(blit, 40);
		drawTypesIcons(blit);
	}
	
	private void drawTypesIcons(Graphics blit) {
		
		String[] pokemonTypes = pokemon.getTypes();
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
		
		String name = this.pokemon.getName();
		String dexNumber = pokemon.getDexNumber();
		
		blit.setColor(Color.BLACK);
		// desenhando nome do pokemon
		blit.setFont(new Font("Verdana", Font.PLAIN, textSize));
		blit.drawString(name, x + 200, y + 25 + textSize);
		
		// desenhando o número da dex do pokemon
		blit.setFont(new Font("Verdana", Font.PLAIN, textSize - 15));
		blit.drawString("#" + dexNumber, x + 200, y + textSize*2 + 17);
	}
	
	private void drawPokemonIcon(Graphics blit) {
		
		int pokeIconX = x + 10;
		int pokeIconY = y + 10;
		int iconHeight = 180;
		int iconWidth = 180;
		
		// desenhar borda cinza do pokemon
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(pokeIconX+15, pokeIconY+15, iconWidth-30, iconHeight-30, 4, 4);
		
		// desenhar corpo cinza claro do pokemon
		blit.setColor(Color.WHITE);
		int boxWidth = iconHeight - 40;
		blit.fillRoundRect(pokeIconX+20, pokeIconY+20, boxWidth, boxWidth, 4, 4);
		
		// desenhando imagem do pokemon
		blit.drawImage(icon, pokeIconX, pokeIconY, iconWidth, 180, null);
	}
	
	private void drawBody(Graphics blit) {
		
		// desenhar borda cinza
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(x, y, width, height, 4, 4);
		
		// desenhar corpo cinza claro
		blit.setColor(new Color(160,160,160));
		blit.fillRoundRect(x+3, y+3, width-6, height-6, 4, 4);
	}
	
	public static void importTypeImages() {
		
		String is;
		BufferedImage typeImage = null;
		types = new LinkedHashMap<String, BufferedImage>();
		
		String[] typeNames = {
			"bug","dark","dragon","electric",
			"fairy","fighting","fire","flying",
			"ghost","grass","ground","ice",
			"normal","poison","psychic","rock",
			"steel","water"
		};
		
		for (int i=0; i<18; i++) {
			is = "res/PokemonTypes/" + typeNames[i] + ".png";
			
			try {typeImage = ImageIO.read(new File(is));}
			catch (Exception e) {e.printStackTrace();}
			
			types.put(typeNames[i], typeImage);
		}
	}
}

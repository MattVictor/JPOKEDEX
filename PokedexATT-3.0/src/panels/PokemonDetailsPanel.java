package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import inputs.Button;
import inputs.PokemonButton;
import main.PanelController;
import pokemon.Pokedex;
import pokemon.Pokemon;

public class PokemonDetailsPanel {
	
	private BufferedImage pokeballIcon;
	private BufferedImage backArrowIcon;
	private BufferedImage fowardArrowIcon;
	private Button backButton;
	
	private static Pokemon pokemon;
	private ArrayList<Button> buttons;
	private ArrayList<PokemonButton> pokemonButtons;
	
	private MultiContentLabel height, weight, abilities, eggGroup;
	
	public PokemonDetailsPanel() {
		
		importImages();
		initClasses();
	}
	
	public void render(Graphics blit) {
		
		drawUpperBar(blit);
		drawButtons(blit);
		drawLabels(blit);
		drawStatsLabel(blit);
		drawPokemonIcon(blit);
		drawPokemonTypes(blit);
		drawPokemonButtons(blit);
		drawPokemonCategory(blit);
		drawArrows(blit);
	}
	
	public void updateInformations(PanelController whereComeFrom, Pokemon newPokemon) {
		
		pokemon = newPokemon;
		updateLabelsInformation();
		updateEvolutions();
		updateDexSequence();
	}
	
	
	// METODOS AUXILIARES
	private void initClasses() {
		
		pokemon = Pokedex.getPokemon(1);
		buttons = new ArrayList<Button>();
		pokemonButtons = new ArrayList<PokemonButton>();
		backButton = new Button(0,10,30,40,40,backArrowIcon);
		buttons.add(backButton);
		
		height = new MultiContentLabel(300,300,100,55,15,"Height");
		weight = new MultiContentLabel(300,380,100,55,15,"Weight");
		abilities = new MultiContentLabel(20,380,230,105,15,"Abilities");
		eggGroup = new MultiContentLabel(20,510,230,85,15,"Egg Group");
	}
	
	private void drawUpperBar(Graphics blit) {
		
		// desenhando barra vermelha no topo
		blit.setColor(new Color(215,15,45));
		blit.fillRect(0, 5, 800, 100);
		blit.setColor(new Color(250,35,50));
		blit.fillRect(0, 0, 800, 100);
		
		// desenhando texto "Pokémon Details"
		blit.setColor(Color.WHITE);
		blit.setFont(new Font("Verdana", Font.PLAIN, 40));
		blit.drawString("Pokémon Details-", 70, 65);
		
		// desenhando nome do pokemon
		blit.setColor(Color.BLACK);
		blit.setFont(new Font("Verdana", Font.BOLD, 30));
		blit.drawString(pokemon.getNOME(), 432, 66);
		
		// desenhando pokebola
		blit.drawImage(pokeballIcon, 720, 20, 60, 60, null);
	}
	
	private void drawLabels(Graphics blit) {
		
		height.render(blit);
		weight.render(blit);
		abilities.render(blit);
		eggGroup.render(blit);
	}
	
	private void drawPokemonIcon(Graphics blit) {
		
		int iconX = 330, iconY = 115, iconSize = 140;
		
		// desenhando borda cinza escuro
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(iconX+17, iconY+17, iconSize-34, iconSize-34, 4, 4);
		
		// desenhando fundo branco
		blit.setColor(Color.WHITE);
		blit.fillRoundRect(iconX+22, iconY+22, iconSize-44, iconSize-44, 4, 4);
		
		//desenhando pokemon
		blit.drawImage(pokemon.getSPRITE(), iconX, iconY, iconSize, iconSize, null);
	}
	
	private void drawPokemonTypes(Graphics blit) {
		
		Map<String,BufferedImage> types = PokemonButton.getTypes();
		
		String[] pokemonTypes = pokemon.getTIPO();
		int x = 400, y = 245;
		
		// percorrendo dicionario com os nomes dos tipos e seus PNGs
		for(Map.Entry<String,BufferedImage> typesEntry : types.entrySet()) {
			
			// para cada tipo do pokemon, checa se ele eh o atual no map
			for(int i=0; i< pokemonTypes.length; i++) {
				
				if (Objects.equals(pokemonTypes[i], typesEntry.getKey())) {
					
					// desenha os tipos, baseado no valor guardado no mapa correspondente aos tipos do poke
					BufferedImage typeIcon = typesEntry.getValue();
					
					// if e else pra garantir que os tipos vão ficar centralizados
					if (pokemonTypes.length > 1) {
						if (i==0) {
						blit.drawImage(typeIcon, x - typeIcon.getWidth()/2 + 10, y, typeIcon.getWidth()/2, 41, null);
						}
						else {
						blit.drawImage(typeIcon, x, y, typeIcon.getWidth()/2, 41, null);
						}
					}
					else {
						blit.drawImage(typeIcon, x - typeIcon.getWidth()/4, y, typeIcon.getWidth()/2, 41, null);
					}
				}
			}
		}
	}
	
	private void drawPokemonCategory(Graphics blit) {
		
		int x = 20,y = 300,width = 230,height = 40, textSize = 20;
		
		// desenhando borda cinza
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(x-3, y-3, width+6, height+6, 15, 15);
		
		// desenhando fundo branco
		blit.setColor(new Color(240,240,240));
		blit.fillRoundRect(x, y, width, height, 15, 15);
		
		// desenhando texto da category
		blit.setColor(Color.BLACK);
		blit.setFont(new Font("Verdana", Font.BOLD, textSize));
		blit.drawString(pokemon.getCATEGORY(), x+10, y + textSize + 5);
	}
	
	private void drawStatsLabel(Graphics blit) {
		
		int x = 440;
		int y = 300;
		int borderOffset = 3;
		int textSize = 20;
		
		// desenhar corpo do label
		blit.setColor(new Color(50,50,50));
		blit.fillRoundRect(x-borderOffset, y-borderOffset, 330+borderOffset*2, 275+borderOffset*2, 15, 15);
		blit.setColor(new Color(160,160,160));
		blit.fillRoundRect(x, y, 330, 275, 15, 15);
		
		// desenhando titulo
		blit.setColor(Color.BLACK);
		blit.setFont(new Font("Verdana", Font.BOLD, textSize));
		blit.drawString("Stats", x+15, y+15+textSize);
		
		// mudando fonte e pegando um array dos stats do pokemon
		blit.setFont(new Font("Verdana", Font.PLAIN, textSize));
		Map<String,Integer> stats = pokemon.getSTATS();
		
		int contador = 0, total = 0;
		String[] statsKeys = new String[6];
		int[] statsValues = new int[6];
		
		for (Map.Entry<String,Integer> stat : stats.entrySet()) {
			
			statsValues[contador] = stat.getValue();
			statsKeys[contador] = stat.getKey();
			total += stat.getValue();
			contador++;
		}
		
		for (int i=0; i<6; i++) {
			
			// desenhando texto dos stats
			blit.setColor(Color.BLACK);
			blit.drawString(statsKeys[i], x+15, y + 75 + i * 30);
			blit.drawString(Integer.toString(statsValues[i]), x+280, y + 75 + i * 30);
			
			// desenhando barra dos stats
			blit.setColor(new Color(215,15,45));
			blit.fillRoundRect(x+95, y + 60 + i * 30, statsValues[i] * 500 / total, 18, 15, 15);
		}
		
		// desenhando total
		blit.setColor(Color.BLACK);
		blit.drawString("TOTAL --------------------- " + total, x+15, y + 255);
	}
	
	private void updateEvolutions() {
		
		pokemonButtons.clear();
		ArrayList<Pokemon> evolutions = new ArrayList<Pokemon>();
		
		if (pokemon.getEVOLUCOES().size() > 1) {
			
			for (String evolution : pokemon.getEVOLUCOES()) {
				evolutions.add(Pokedex.getPokemon(evolution));
			}
			
			int buttonsDistancy;
			for (int i=0; i<evolutions.size(); i++) {
				buttonsDistancy = 800/(evolutions.size()+1);
				pokemonButtons.add(new PokemonButton(buttonsDistancy*(i+1)-50, 670, 100, 100, evolutions.get(i)));
			}
			
			if(buttons.size()>1) buttons.remove(buttons.size()-1);
		}
		else {
			buttons.add(new Button(buttons.size(), 200, 680, 400, 80, "THIS POKÉMON DOENST EVOLVE  ", 20));
		}
	}
	
	private void drawArrows(Graphics blit) {
		
		double evolutionsQnt = pokemon.getEVOLUCOES().size();
		int arrowsDistancy = 800/ ((int)evolutionsQnt+1);
		for (int i=0; i< evolutionsQnt-1; i++) {
			
			//desenhando setas da evolucao de pokemon
			blit.drawImage(fowardArrowIcon, arrowsDistancy*(i+1)+ (int)(140 * (1.55-evolutionsQnt/3)), 705, 40, 40, null);
		}
	}
	
	private void updateDexSequence() {
		
		if (Pokedex.getPokemon(pokemon.getID()-1) != null) {
			pokemonButtons.add(new PokemonButton(70, 115, 100, 100, Pokedex.getPokemon(pokemon.getID()-1)));
		}
		
		if (Pokedex.getPokemon(pokemon.getID()+1) != null) {
			pokemonButtons.add(new PokemonButton(630, 115, 100, 100, Pokedex.getPokemon(pokemon.getID()+1)));
		}
	}
	
	private void drawButtons(Graphics blit) {
		
		Button[] currentButtons = new Button[buttons.toArray().length];
		System.arraycopy(buttons.toArray(), 0, currentButtons, 0, buttons.toArray().length);
		
		for(Button button : currentButtons) {
			button.drawButton(blit);
		}
	}
	
	private void drawPokemonButtons(Graphics blit) {
		
		PokemonButton[] currentPokeButtons = new PokemonButton[pokemonButtons.toArray().length];
		System.arraycopy(pokemonButtons.toArray(), 0, currentPokeButtons, 0, pokemonButtons.toArray().length);
		
		for (PokemonButton pokeButton : currentPokeButtons) {
			pokeButton.drawPokemonButton(blit);
		}
	}
	
	private void importImages() {
		
		InputStream is = getClass().getResourceAsStream("/IconPokeball.png");
		
		try {pokeballIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
		
		is = getClass().getResourceAsStream("/backArrowIcon.png");
		
		try {backArrowIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
		
		is = getClass().getResourceAsStream("/fowardArrowIcon.png");
		
		try {fowardArrowIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}
	
	private void updateLabelsInformation() {
		
		height.updateInformation(Double.toString(pokemon.getALTURA()));
		weight.updateInformation(Double.toString(pokemon.getPESO()));
		abilities.updateInformation(pokemon.getHABILIDADES());
		eggGroup.updateInformation(pokemon.getEGG_GROUPS());
	}

	//GETTERS E SETTERS
	public ArrayList<Button> getButtons() {
		return buttons;
	}
	
	public ArrayList<PokemonButton> getPokemonButtons() {
		return pokemonButtons;
	}

	public static void setPokemon(Pokemon pokemon) {
		PokemonDetailsPanel.pokemon = pokemon;
	}
}

package inputs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pokemon {
	
	private BufferedImage icon;
	private String name;
	private String dexNumber;
	private String[] types;
	
	private static ArrayList<Pokemon> pokemons;
	
	public static void initiatePokemons() {
		
		pokemons = new ArrayList<Pokemon>();
		
		pokemons.add(new Pokemon(importPokemonSprite("bulba"), "Bulbassaur", "001", new String[] {"grass", "poison"}));
		pokemons.add(new Pokemon(importPokemonSprite("ivyssaur"), "Ivyssaur", "002", new String[] {"grass", "poison"}));
	}
	
	
	public Pokemon(BufferedImage icon, String name, String dexNumber, String[] types) {
		super();
		this.icon = icon;
		this.name = name;
		this.dexNumber = dexNumber;
		this.types = types;
	}
	
	
	// METODOS AUXILIARES
	
	private static BufferedImage importPokemonSprite(String pokemonName) {
		
		String is = "res/" + pokemonName + ".png";
		
		try {return ImageIO.read(new File(is));}
		catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	// GETTERS E SETTERS
	
	public BufferedImage getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public String getDexNumber() {
		return dexNumber;
	}

	public String[] getTypes() {
		return types;
	}
	
	public static ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}
}

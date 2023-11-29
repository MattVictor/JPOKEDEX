package pokemon;

import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pokedex {

	public static HashMap<Integer,Pokemon> dex = new HashMap<Integer,Pokemon>();

	public static void initiate() {

		JSONArray results = PokeApiClient.getPokemonData(PokeApiClient.allPokemonURL).getJSONArray("results");
		
		for(int i = 0; i < 255; i++){
			if (i == 149 || i < 24) {
				dex.put(i+1,new Pokemon(results.getJSONObject(i).getString("name"),
						i+1,
						results.getJSONObject(i).getString("url")));
				System.out.println("importou o pokemon " + (i+1) + " na dex");
			}
		}
	}

	public static void printAllPokemon(){
		for(Map.Entry <Integer,Pokemon> pokemon : dex.entrySet()){
			System.out.println("ID: "+ pokemon.getKey() + " / " + pokemon.getValue().getNOME());
		}
	}

	public static Pokemon getPokemon(int id){
		
		Pokemon poke = dex.get(id);
		if(poke != null) {
			if(!poke.isCOMPLETEDATA()){
				poke.completePokemonData();
			}
		}
		return poke;
	}
	
	public static Pokemon getPokemon(String nome) {
		
		for (Pokemon pokemon : dex.values()) {
			if (Objects.equals(pokemon.getNOME(), nome)) {
				if(!pokemon.isCOMPLETEDATA()){
					pokemon.completePokemonData();
				}
				return pokemon;
			}
		}
		return null;
	}

	public static int getPokedexSize() {
		return dex.size();
	}
}
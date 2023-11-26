package Poke;

import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public class Pokedex {
    public HashMap<Integer,Pokemon> dex;

    public Pokedex(){
        JSONArray results = PokeApiClient.getPokemonData(PokeApiClient.allPokemonURL).getJSONArray("results");
        dex = new HashMap<Integer,Pokemon>();
        for(int i = 0; i < 1017; i++){
            dex.put(i+1,new Pokemon(results.getJSONObject(i).getString("name"),
                    i+1,
                    results.getJSONObject(i).getString("url")));
            System.out.println(i);
        }
    }

    public void printAllPokemon(){
        for(Map.Entry <Integer,Pokemon> pokemon : dex.entrySet()){
            System.out.println("ID: "+ pokemon.getKey() + " / " + pokemon.getValue().getNOME());
        }
    }

    public Pokemon getPokemon(int id){
        if(!dex.get(id).isCOMPLETEDATA()){
            dex.get(id).completePokemonData();
        }
        return dex.get(id);
    }
}

package pokemon;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pokedex {
    public static HashMap<Integer, Pokemon> dex;

    public static void initiate(){
        String informationstring;
        try {
            informationstring = IOUtils.toString(new FileReader("data.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject data = new JSONObject(informationstring);

        dex = new HashMap<Integer, Pokemon>();
        for(int i = 0; i < 1017; i++){
            dex.put(i+1,new Pokemon(data.getJSONArray("pokemons").getJSONObject(i)));
        }
    }

    public void printAllPokemon(){
        for(Map.Entry <Integer, Pokemon> pokemon : dex.entrySet()){
            System.out.println("ID: "+ pokemon.getKey() + " / " + pokemon.getValue().getNAME());
        }
    }

    public static Pokemon getPokemon(String nome) {

        for (Pokemon pokemon : dex.values()) {
            if (Objects.equals(pokemon.getNAME(), nome)) {
                return pokemon;
            }
        }
        return null;
    }

    public static Pokemon getPokemon(int id){
        return dex.get(id);
    }
}

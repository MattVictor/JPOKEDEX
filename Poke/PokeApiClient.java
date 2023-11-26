package Poke;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class PokeApiClient {
    public static final String pokemonSpeciesURL = "https://pokeapi.co/api/v2/pokemon-species/";
    public static final String evolutionChainURL = "https://pokeapi.co/api/v2/evolution-chain/";
    public static final String allPokemonURL = "https://pokeapi.co/api/v2/pokemon?limit=1017&offset=0";
    public static final String spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    public static JSONObject getPokemonData(String pokemonUrl){
        try {
            URL url=new URI(pokemonUrl).toURL();
            StringBuilder informationstring= new StringBuilder();
            Scanner sc=new Scanner(url.openStream());
            while(sc.hasNext()) {
                informationstring.append(sc.nextLine());
            }
            sc.close();
            return new JSONObject(String.valueOf(informationstring));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getSprite(int id){
        try {
            URL url = new URI(spriteURL + id + ".png").toURL();
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

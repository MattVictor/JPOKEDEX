package Poke;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import javax.imageio.ImageIO;

public class Pokemon{
    int ID;
    String NOME;
    String[] TIPO;
    String[] EVOLUCOES; // TODO
    String[] HABILIDADES;
    String[] EGG_GROUPS;
    int ALTURA;
    int PESO;
    HashMap<String,Integer>STATS;
    BufferedImage SPRITE;


    public Pokemon(int id) {
        this.setID(id);

        JSONObject pokedata = pokemonData(id);
        JSONObject pokeSpeciesData = pokemonSpeciesData(id);

        this.setNOME(pokedata.get("name").toString());

        this.TIPO = new String[pokedata.getJSONArray("types").length()];
        this.setTIPO(pokedata);

        this.HABILIDADES = new String[pokedata.getJSONArray("abilities").length()];
        this.setHABILIDADES(pokedata);

        this.EGG_GROUPS = new String[pokeSpeciesData.getJSONArray("egg_groups").length()];
        this.setEGG_GROUPS(pokeSpeciesData);

        this.setALTURA(pokedata.getInt("height"));

        this.setPESO(pokedata.getInt("weight"));

        STATS = new HashMap<String,Integer>();
        this.setSTATS(pokedata);

        this.setSPRITE(id);
    }

    public JSONObject pokemonData(int id){
        try {
            URL url=new URI("https://pokeapi.co/api/v2/pokemon/"+id).toURL();
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responsecode=con.getResponseCode();
            if(responsecode!=200) {
                System.out.println("Error"+ responsecode);
            }
            else {
                StringBuilder informationstring= new StringBuilder();
                Scanner sc=new Scanner(url.openStream());
                while(sc.hasNext()) {
                    informationstring.append(sc.nextLine());
                }
                sc.close();
                return new JSONObject(String.valueOf(informationstring));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject pokemonSpeciesData(int id){
        try {
            URL url=new URI("https://pokeapi.co/api/v2/pokemon-species/"+id).toURL();
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responsecode=con.getResponseCode();
            if(responsecode!=200) {
                System.out.println("Error"+ responsecode);
            }
            else {
                StringBuilder informationstring= new StringBuilder();
                Scanner sc=new Scanner(url.openStream());
                while(sc.hasNext()) {
                    informationstring.append(sc.nextLine());
                }
                sc.close();
                return new JSONObject(String.valueOf(informationstring));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String[] getTIPO() {
        return TIPO;
    }

    public void setTIPO(JSONObject data) {
        for(int i = 0; i < data.getJSONArray("types").length(); i++){
            this.TIPO[i] = data.getJSONArray("types").getJSONObject(i).getJSONObject("type").getString("name");
        }
    }

    public String[] getEVOLUCOES() {
        return EVOLUCOES;
    }

    public void setEVOLUCOES(String[] EVOLUCOES) {
        this.EVOLUCOES = EVOLUCOES; // TODO
    }

    public String[] getHABILIDADES() {
        return HABILIDADES;
    }

    public void setHABILIDADES(JSONObject data) {
        for(int i = 0; i < data.getJSONArray("abilities").length(); i++){
            this.HABILIDADES[i] = data.getJSONArray("abilities").getJSONObject(i).getJSONObject("ability").getString("name");
        }
    }

    public String[] getEGG_GROUPS() {
        return EGG_GROUPS;
    }

    public void setEGG_GROUPS(JSONObject data) {
        for(int i = 0; i < data.getJSONArray("egg_groups").length(); i++){
            this.EGG_GROUPS[i] = data.getJSONArray("egg_groups").getJSONObject(i).getString("name");
        }
    }

    public int getALTURA() {
        return ALTURA;
    }

    public void setALTURA(int ALTURA) {
        this.ALTURA = ALTURA;
    }

    public double getPESO() {
        return PESO;
    }

    public void setPESO(int PESO) {
        this.PESO = PESO;
    }

    public HashMap<String, Integer> getSTATS() {
        return STATS;
    }

    public void setSTATS(JSONObject data) {
        STATS.put("hp",data.getJSONArray("stats").getJSONObject(0).getInt("base_stat"));
        STATS.put("attack",data.getJSONArray("stats").getJSONObject(1).getInt("base_stat"));
        STATS.put("defense",data.getJSONArray("stats").getJSONObject(2).getInt("base_stat"));
        STATS.put("special-attack",data.getJSONArray("stats").getJSONObject(3).getInt("base_stat"));
        STATS.put("special-defense",data.getJSONArray("stats").getJSONObject(4).getInt("base_stat"));
        STATS.put("speed",data.getJSONArray("stats").getJSONObject(5).getInt("base_stat"));
    }

    public BufferedImage getSPRITE() {
        return SPRITE;
    }

    public void setSPRITE(int id) {
        try {
            URL url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+ id + ".png");
            SPRITE = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return NOME + "_" + ID;
    }

    public void printPokemon(){
        System.out.println("Nome: " + NOME);
        System.out.println("Altura: " + ALTURA);
        System.out.println("Peso: " + PESO);
        for(String tipo : TIPO){
            System.out.println("Tipo: " + tipo);
        }
        for(String habilidade : HABILIDADES){
            System.out.println("Habilidade: " + habilidade);
        }
        for(String egg : EGG_GROUPS){
            System.out.println("Egg Group: " + egg);
        }
        for(Map.Entry<String,Integer> stat: STATS.entrySet()){
            System.out.println(stat.getKey() +": " + stat.getValue());
        }
    }
}

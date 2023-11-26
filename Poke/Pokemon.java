package Poke;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Pokemon{
    private final String URL;
    private int ID;
    private String NOME;
    private String[] TIPO;
    private String[] EVOLUCOES; // TODO
    private String[] HABILIDADES;
    private String[] EGG_GROUPS;
    private int ALTURA;
    private int PESO;
    private HashMap<String,Integer>STATS;
    private BufferedImage SPRITE;
    private boolean COMPLETEDATA;

    public Pokemon(String nome, int id, String url){
        this.URL = url;
        this.NOME = nome;
        this.ID = id;
        //this.setSPRITE(id);
        COMPLETEDATA = false;
    }

    public void completePokemonData() {
        JSONObject pokedata = PokeApiClient.getPokemonData(URL);
        JSONObject pokeSpeciesData = PokeApiClient.getPokemonData(PokeApiClient.pokemonSpeciesURL + ID);

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

        COMPLETEDATA = true;
    }

    public String getURL() {
        return URL;
    }

    public int getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
    }

    public String[] getTIPO() {
        return TIPO;
    }

    public String[] getEVOLUCOES() {
        return EVOLUCOES;
    }

    public String[] getHABILIDADES() {
        return HABILIDADES;
    }

    public String[] getEGG_GROUPS() {
        return EGG_GROUPS;
    }

    public int getALTURA() {
        return ALTURA;
    }

    public double getPESO() {
        return PESO;
    }

    public HashMap<String, Integer> getSTATS() {
        return STATS;
    }

    public BufferedImage getSPRITE() {
        return SPRITE;
    }

    public boolean isCOMPLETEDATA() {
        return COMPLETEDATA;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public void setTIPO(JSONObject data) {
        JSONArray tipos = data.getJSONArray("types");
        this.TIPO = new String[tipos.length()];
        for(int i = 0; i < tipos.length(); i++){
            this.TIPO[i] = tipos.getJSONObject(i).getJSONObject("type").getString("name");
        }
    }

    public void setEVOLUCOES(String[] EVOLUCOES) {
        this.EVOLUCOES = EVOLUCOES; // TODO
    }

    public void setHABILIDADES(JSONObject data) {
        for(int i = 0; i < data.getJSONArray("abilities").length(); i++){
            this.HABILIDADES[i] = data.getJSONArray("abilities").getJSONObject(i).getJSONObject("ability").getString("name");
        }
    }

    public void setEGG_GROUPS(JSONObject data) {
        for(int i = 0; i < data.getJSONArray("egg_groups").length(); i++){
            this.EGG_GROUPS[i] = data.getJSONArray("egg_groups").getJSONObject(i).getString("name");
        }
    }

    public void setALTURA(int ALTURA) {
        this.ALTURA = ALTURA;
    }

    public void setPESO(int PESO) {
        this.PESO = PESO;
    }

    public void setSTATS(JSONObject data) {
        STATS.put("hp",data.getJSONArray("stats").getJSONObject(0).getInt("base_stat"));
        STATS.put("attack",data.getJSONArray("stats").getJSONObject(1).getInt("base_stat"));
        STATS.put("defense",data.getJSONArray("stats").getJSONObject(2).getInt("base_stat"));
        STATS.put("special-attack",data.getJSONArray("stats").getJSONObject(3).getInt("base_stat"));
        STATS.put("special-defense",data.getJSONArray("stats").getJSONObject(4).getInt("base_stat"));
        STATS.put("speed",data.getJSONArray("stats").getJSONObject(5).getInt("base_stat"));
    }

    public void setSPRITE(int id) {
        SPRITE = PokeApiClient.getSprite(ID);
    }

    public String toString(){
        return ID + " - " + NOME;
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

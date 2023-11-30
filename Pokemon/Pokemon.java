package pokemon;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class Pokemon {
    private int ID; //APPEND
    private String DEXNUMBER;
    private String NAME; //APPEND
    private String[] TYPES; //ARRAY
    private String CATEGORY; //APPEND
    private ArrayList<String> EVOLUTION_CHAIN; // TODO //ARRAY
    private String[] ABILITIES; //ARRAY
    private String[] EGG_GROUPS; //ARRAY
    private int HEIGHT; //APPEND
    private int WEIGHT; //APPEND
    private LinkedHashMap<String,Integer> STATS; //ARRAY
    private BufferedImage SPRITE; // APPEND


    public Pokemon(JSONObject data) {
        this.ID = parseInt(treatString(data.get("id").toString()));

        this.DEXNUMBER = generateDexNumber(this.ID);

        this.NAME = treatString(data.get("name").toString());

        this.TYPES = new String[data.getJSONArray("types").length()];
        for(int i = 0; i < data.getJSONArray("types").length(); i++){
            TYPES[i] = treatString(data.getJSONArray("types").getJSONObject(i).get("type").toString());
        }

        if(this.ID < 1011)
            this.CATEGORY = data.getString("category");
        else
            this.CATEGORY = "Pokemon does not have Category";

        //this.CATEGORY = data.getString("category");
        this.EVOLUTION_CHAIN = new ArrayList<String>();
        for(int i = 0; i < data.getJSONArray("evolution_chain").length(); i++){
            this.EVOLUTION_CHAIN.add(treatString(data.getJSONArray("evolution_chain").getJSONObject(i).get("form").toString()));
        }

        this.ABILITIES = new String[data.getJSONArray("abilities").length()];
        for(int i = 0; i < data.getJSONArray("abilities").length(); i++){
            this.ABILITIES[i] = (treatString(data.getJSONArray("abilities").getJSONObject(i).get("ability").toString()));
        }

        this.EGG_GROUPS = new String[data.getJSONArray("egg_groups").length()];
        for(int i = 0; i < data.getJSONArray("egg_groups").length(); i++){
            this.EGG_GROUPS[i] = (treatString(data.getJSONArray("egg_groups").getJSONObject(i).get("egg_group").toString()));
        }

        this.HEIGHT = parseInt(treatString(data.get("height").toString()));

        this.WEIGHT = parseInt(treatString(data.get("weight").toString()));
        parseInt(treatString(data.getJSONArray("stats").getJSONObject(0).get("base_stat").toString()));

        STATS = new LinkedHashMap<String,Integer>();
        STATS.put("HP",parseInt(treatString(data.getJSONArray("stats").getJSONObject(0).get("base_stat").toString())));
        STATS.put("ATK",parseInt(treatString(data.getJSONArray("stats").getJSONObject(1).get("base_stat").toString())));
        STATS.put("DEF",parseInt(treatString(data.getJSONArray("stats").getJSONObject(2).get("base_stat").toString())));
        STATS.put("SP-ATK",parseInt(treatString(data.getJSONArray("stats").getJSONObject(3).get("base_stat").toString())));
        STATS.put("SP-DEF",parseInt(treatString(data.getJSONArray("stats").getJSONObject(4).get("base_stat").toString())));
        STATS.put("SPEED",parseInt(treatString(data.getJSONArray("stats").getJSONObject(5).get("base_stat").toString())));

        this.SPRITE = this.setSPRITE(this.ID);
    }

    public String getNAME() {
        return NAME;
    }

    public String[] getTYPES() {
        return TYPES;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public ArrayList<String> getEVOLUTION_CHAIN() {
        return EVOLUTION_CHAIN;
    }

    public String[] getABILITIES() {
        return ABILITIES;
    }

    public String[] getEGG_GROUPS() {
        return EGG_GROUPS;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWEIGHT() {
        return WEIGHT;
    }

    public LinkedHashMap<String, Integer> getSTATS() {
        return STATS;
    }

    public BufferedImage getSPRITE() {
        return SPRITE;
    }

    private BufferedImage setSPRITE(int id) {
        BufferedImage image = null;
        try {
            InputStream is = getClass().getResourceAsStream("/PokemonImages/"+id+".png");
            image = ImageIO.read(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    private String treatString(String string){
        return string.replace("[","").replace("]","");
    }

    public int getID() {
        return ID;
    }

    public String getDEXNUMBER() {
        return DEXNUMBER;
    }


    private String generateDexNumber(int id) {
        if (id < 10) return "#000" + Integer.toString(id);
        else if (id < 100) return "#00" + Integer.toString(id);
        else if (id < 1000) return "#0" + Integer.toString(id);
        else return "#" + Integer.toString(id);
    }

    public String toString(){
        return ID + " - " + NAME;
    }

    public void printPokemon(){
        System.out.println("Nome: " + NAME);
        System.out.println("Altura: " + HEIGHT);
        System.out.println("Peso: " + WEIGHT);
        for(String tipo : TYPES){
            System.out.println("Tipo: " + tipo);
        }
        System.out.println("Evoluções: ");
        for(String evo : EVOLUTION_CHAIN){
            System.out.printf(evo + " / ");
        }
        System.out.println();

        for(String habilidade : ABILITIES){
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

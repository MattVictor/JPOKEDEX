package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import inputs.Button;

public class ChartPanel {
	
	// true = atk, false = def
	private static boolean chartMode;
	private static String actualChart;
	
	private static Map<String,Rectangle> hitboxes;
	private Map<String,BufferedImage> attackCharts;
	private Map<String,BufferedImage> defenseCharts;
	
	private ArrayList<Button> buttons;
	private Button atkButton, defButton;
	private Button backButton;
	
	private BufferedImage chartComplete;
	private BufferedImage backArrowIcon;
	private BufferedImage pokeballIcon;
	
	public ChartPanel() {
		
		importImages();
		initClasses();
		initiateHitboxes();
		importCharts();
	}
	
	public void render(Graphics blit) {
		
		drawUpperBar(blit);
		drawButtons(blit);
		drawChartBase(blit);
		drawActualChart(blit);
	}
	

	// METODOS AUXILIARES
	private void initClasses() {
		
		actualChart = "steel";
		chartMode = false;
		
		buttons = new ArrayList<Button>();
		attackCharts = new LinkedHashMap<String,BufferedImage>();
		defenseCharts = new LinkedHashMap<String,BufferedImage>();
		hitboxes = new LinkedHashMap<String, Rectangle>();
		
		backButton = new Button(0,10,30,40,40,backArrowIcon);
		atkButton = new Button(1,585,10,100,40,"Attack",20);
		defButton = new Button(2,585,55,100,40,"Defense",20);
		
		buttons.add(backButton);
		buttons.add(atkButton);
		buttons.add(defButton);
	}
	
	private void drawButtons(Graphics blit) {
		
		for(int i=0; i< buttons.size(); i++) {
			buttons.get(i).drawButton(blit);
		}
	}
	
	private void drawUpperBar(Graphics blit) {
		
		// desenhando barra vermelha no topo
		blit.setColor(new Color(215,15,45));
		blit.fillRect(0, 5, 800, 100);
		blit.setColor(new Color(250,35,50));
		blit.fillRect(0, 0, 800, 100);
		
		// desenhando texto "Pokémon chart"
		blit.setColor(Color.WHITE);
		blit.setFont(new Font("Verdana", Font.PLAIN, 40));
		blit.drawString("Pokémon Chart-", 70, 65);
		
		// desenhando modo atual da chart
		blit.setColor(Color.BLACK);
		blit.setFont(new Font("Verdana", Font.BOLD, 30));
		blit.drawString(chartMode? "Attack" : "Defense", 402, 66);
		
		// desenhando pokebola
		blit.drawImage(pokeballIcon, 720, 20, 60, 60, null);
		
		// desenhando circulo grande
		blit.setColor(new Color(50,50,50));
		blit.fillOval(57, 109, 686, 686);
		blit.setColor(new Color(180,180,180));
		blit.fillOval(60, 112, 680, 680);
	}
	
	private void drawChartBase(Graphics blit) {
		
		blit.drawImage(chartComplete, 60, 112, 680, 680, null);
	}
	
	private void drawActualChart(Graphics blit) {
		
		if (chartMode) { // significa modo "ataque"
			
			blit.drawImage(attackCharts.get(actualChart), 60, 112, 680, 680, null);
		}
		else {
			
			blit.drawImage(defenseCharts.get(actualChart), 60, 112, 680, 680, null);
		}
	}
	
	private void importCharts() {
		
		String[] typeNames = {
			"psychic","bug","dark","dragon","electric",
			"fairy","fighting","fire","flying",
			"ghost","grass","ground","ice",
			"normal","poison","rock",
			"steel","water"
		};
		
		BufferedImage chart = null;
		String is;
		
		for (int i=0; i<18; i++) {
			is = "res/chartATKs/" + typeNames[i] + "ATK.png";
			
			try {chart = ImageIO.read(new File(is));}
			catch (Exception e) {e.printStackTrace();}
			
			attackCharts.put(typeNames[i], chart);
			
			is = "res/chartDEFs/" + typeNames[i] + "DEF.png";
			
			try {chart = ImageIO.read(new File(is));}
			catch (Exception e) {e.printStackTrace();}
			
			defenseCharts.put(typeNames[i], chart);
		}
	}
	
	private void importImages() {
		
		InputStream is = getClass().getResourceAsStream("/pokemonTypeChartComplete.png");
		
		try {chartComplete = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
		
		is = getClass().getResourceAsStream("/backArrowIcon.png");
		
		try {backArrowIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
		
		is = getClass().getResourceAsStream("/IconPokeball.png");
		
		try {pokeballIcon = ImageIO.read(is);}
		catch (Exception e) {e.printStackTrace();}
	}
	
	private void initiateHitboxes() {
		
		String[] types = {
			"dark","bug","flying","ice","rock",
			"dragon","normal","fighting","fairy",
			"steel","fire","water","electric","ground",
			"grass","poison","psychic","ghost"
		};
		
		// todas as posicoes das hitboxes da chart
		int[][] hitboxesSpots = {
			{232,182},{327,151},{419,152},{509,184},{583,248},
			{624,328},{639,419},{623,511},{578,595},{509,652},
			{423,688},{324,692},{233,656},{160,596},{114,516},
			{95,420},{115,322},{163,240}
		};
		
		for (int i=0; i<18; i++) {
			hitboxes.put(types[i], new Rectangle(hitboxesSpots[i][0],hitboxesSpots[i][1],70,70));
		}
	}
	
	// GETTERS E SETTERS
	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public static void setChartMode(boolean chartMode) {
		ChartPanel.chartMode = chartMode;
	}

	public static void setActualChart(String actualChart) {
		ChartPanel.actualChart = actualChart;
	}

	public static Map<String, Rectangle> getHitboxes() {
		return hitboxes;
	}
}

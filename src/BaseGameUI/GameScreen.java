package BaseGameUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import Util.TowerUtil;
import Util.DrawTowerUtil;
import Util.Point;
import Model.ElectricTower;
import Model.FireTower;
import Model.IceTower;
import Model.Tower;
import Model.WoodTower;
import GameData.MapData;
import GameData.StaticGameInfo;
/**
 * This is a basic game screen which after users select a map
 * 
 * @author peilin
 *
 */
public class GameScreen extends JPanel implements Runnable, MouseMotionListener, MouseListener{
	
	private int focusX = -100;							// current the X-axis of the mouse
	private int focusY = -100;							// current the Y-axis of the mouse
	private int map_row;
	private int map_col;
	private int money;
	private int round;
	private int changeTowerType;
	private int towerType;
	private int upX;									// the X-axis of the tower which users want to update
	private int upY;									// the Y-axis of the tower which users want to update
	private int[][] path = new int[100][100];
	private boolean up;									// up the tower 
	private boolean broken;								// sell the tower			
	private boolean atTools;
	private boolean drawTowerTools;
	private boolean drawMoney;	
	private Tower focusTower;							// a tower when users mouse clicked it 
	private List<Point> toolsList;
	private List<Tower> towerList;
	private JButton start,back;
	private MainScreen mainscreen;
	public static String MAP_PATH;
	Thread GST = new Thread(this);
	
	public GameScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		init();	
		this.addMouseListener(this);
	}
	
	/**
	 * A method of initialize the value
	 * 
	 */
	public void init(){
		GST.start();
		money = 3000;
		round = 1;
		upX = -100;
		upY = -100;
		up = false;
		broken = false;
		atTools = false;
		drawTowerTools = false;
		changeTowerType = -1;
		drawMoney = true;
		towerType = -1;
		toolsList = new ArrayList<Point>();
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE * 8));
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE + 25, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE
				* 8));
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE * 3, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE
				* 8));
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE * 4 + 25, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE
				* 8));
		this.setLayout(null);
		start = new JButton("Start Game");
		back = new JButton("Back");
		this.add(start);
		this.add(back);
		start.setBounds(700,515,100,30);
		back.setBounds(700,550,100,30);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainscreen.removeAll();
				mainscreen.add(new StartScreen(mainscreen));
				mainscreen.validate();
				mainscreen.repaint();
			}
		});
		towerList = new ArrayList<Tower>();
		this.addMouseMotionListener(this);
		
		try {
			loadMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * a method of add a tower to tower list 
	 * @param type   a tower's type
	 * @param x		 the position of the tower's x-axis	
	 * @param y		 the position of tower's y-axis
	 * @param size	 the size of the tower	
	 */
	public void addTower(int type, int x, int y, int size) {
		Tower tower = null;
		if (type == 0) {
			tower = new IceTower(x, y, size);
		}
		if (type == 1) {
			tower = new FireTower(x, y, size);
		}
		if (type == 2) {
			tower = new ElectricTower(x, y, size);
		}
		if (type == 3) {
			tower = new WoodTower(x, y, size);
		}
		towerList.add(tower);
	}

	/**
	 * This method is loaded a map's information when users select it  
	 * @throws FileNotFoundException
	 */
	public void loadMap() throws FileNotFoundException{
		MapData mapinfo = new MapData(MAP_PATH);
		map_row = mapinfo.getGridRow();
		map_col = mapinfo.getGridCol();
		int new_path[][] = new int [map_row][map_col];
		InputStream in = new FileInputStream(MAP_PATH); 
        Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){   	
        	for(int i=0;i < mapinfo.getGridRow();i++){
        		for(int j=0;j < mapinfo.getGridCol();j++){
        			new_path[i][j] = scanner.nextInt();
        		}
        	}
        	setPath(new_path);
        }
       try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is a method of paint 
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, StaticGameInfo.FRAME_WIDTH, StaticGameInfo.FRAME_HEIGHT);
		drawPath(g);
		drawGrid(g);
		drawTools(g);
		drawTowers(g);
		drawTowersTools(g);
		drawUpOrDown(g);
		drawMoney(g);
		drawTowerInspectPanel(g);
		drawTowerInspectInfo(g);
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
	}

	/**
	 * This method is draw a kind of buttons of update and broken
	 * 
	 * @param g2
	 */
	private void drawUpOrDown(Graphics g2){
		if (upX != -100 && upY != -100 && !drawTowerTools) {
			g2.setColor(Color.white);
			g2.fillRect(upX , upY, 50, 25);
			g2.drawOval(upX-(focusTower.getRange()/2)+25, upY-(focusTower.getRange()/2)+25, focusTower.getRange(), focusTower.getRange());
			if (up) {
				g2.setColor(Color.GREEN);
				g2.fillRect(upX, upY, 25, 25);
			}
			if (broken) {
				g2.setColor(Color.red);
				g2.fillRect(upX + 25, upY, 25, 25);
			}
			g2.setColor(Color.black);
			g2.drawLine(upX + StaticGameInfo.GRID_SIZE / 2, upY, upX + StaticGameInfo.GRID_SIZE  / 2,
					upY + 25);
			Font font = new Font("Times New Roman", 5, 10);
			g2.setFont(font);
			if (focusTower.getLevel() < 6) {
				g2.drawString("UP", upX+6, upY + 10);
				g2.drawString("$:"+focusTower.getPrice(), upX+2, upY + 20);
			}
			g2.drawString("SALE", upX + 25, upY + 10);	
			}
	}
	
	/**
	 * This method is draw current money which the player hold
	 * @param g2
	 */
	private void drawMoney(Graphics g2){
		if (drawMoney) {
			Font font = new Font("TimesRoman", 30, 25);
			g2.setFont(font);
			g2.setColor(Color.black);
			g2.drawString("$" + getMoney(), StaticGameInfo.GAMELOCATION_X, 
					StaticGameInfo.GAMELOCATION_Y + 10 * StaticGameInfo.GRID_SIZE);
		}
		Font font = new Font("TimesRoman", 30, 25);
		g2.setColor(Color.black);
		g2.setFont(font);
		g2.drawString("round" + getRound(), StaticGameInfo.GAMELOCATION_X + 3 * StaticGameInfo.GRID_SIZE ,
				StaticGameInfo.GAMELOCATION_Y + 10 * StaticGameInfo.GRID_SIZE);
	}
	
	/**
	 * This method is draw a panel which will hold the information of a tower
	 * 
	 * @param g2
	 */
	private void drawTowerInspectPanel(Graphics g2) {
		Font font = new Font("TimesRoman", 25, 15);
		g2.setFont(font);
		g2.drawString("Tower Inspect Panel", 
				StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE-1, 
				StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE-12);
		g2.drawRect(StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE-5,
				StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE-30, 155, 30);
		g2.drawRect(StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE, 
				StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE, 300, 100);
		g2.drawRect(StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE-5, 
				StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE-5, 310, 110);
		g2.drawLine(StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 150, 
				StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE,
				StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 150, 
				StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 100);
	}
	
	/**
	 * This method is display a tower's information 
	 * 
	 * @param g2
	 */
	private void drawTowerInspectInfo(Graphics g2) {
		if (upX != -100 && upY != -100 && !drawTowerTools){
			Font font = new Font("TimesRoman", 30, 15);
			g2.setFont(font);
			g2.setColor(Color.black);
			g2.drawString("Level: " + focusTower.getLevel(), 
					StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 5, 
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 15);
			g2.drawString("Power: " + focusTower.getPower(), 
					StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 5,
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 35);
			g2.drawString("Refund: " + "100%", 
					StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 5,
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 55);
			g2.drawString("Speical: " + focusTower.getSpecialEffects(), 
					StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 5,
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 75);
			g2.drawString("Range: " + focusTower.getRange(), 
					StaticGameInfo.GAMELOCATION_X + 6 * StaticGameInfo.GRID_SIZE + 5,
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 95);
			g2.drawString("FightNum: " + focusTower.getFightNum(), 
					StaticGameInfo.GAMELOCATION_X + 9 * StaticGameInfo.GRID_SIZE + 5, 
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 15);
			g2.drawString("Speed: " + focusTower.getSpeed(), 
					StaticGameInfo.GAMELOCATION_X + 9 * StaticGameInfo.GRID_SIZE + 5,
					StaticGameInfo.GAMELOCATION_Y + 8 * StaticGameInfo.GRID_SIZE + 35);
		}	
	}

	/**
	 * This method is after player choose a tower, it can be displayed on the game map following the mouse 
	 * @param g2
	 */
	private void drawTowersTools(Graphics g2){
		if (drawTowerTools) {
			DrawTowerUtil.drawTowerByType(towerType, 1, g2, focusX, focusY,
					StaticGameInfo.GRID_SIZE);
			g2.setColor(Color.WHITE);
		}	
	}
	
	/**
	 * This method is draw towers and the money which is the price of the tower on the toolbar  
	 * @param g2
	 */
	private void drawTools(Graphics g2){
		Font font = new Font("STYLE_BOLD", 5, 12);
		g2.setFont(font);
		for (int i = 0; i < toolsList.size(); i++) {
			DrawTowerUtil.drawTowerByType(i, 1, g2, toolsList.get(i).getX(),
					toolsList.get(i).getY(), StaticGameInfo.GRID_SIZE);
			g2.setColor(Color.black);
			g2.drawString("" +"$"+ TowerUtil.getPriceByTowerType(i), toolsList.get(
					i).getX() + 13, toolsList.get(i).getY() + 60);
		}
	}
	
	/**
	 * This method is draw a tower on the game map
	 * @param g2
	 */
	private void drawTowers(Graphics g2){
		for (int i = 0; i < towerList.size(); i++) {
			Tower tower = towerList.get(i);
			if (tower != null) {
				DrawTowerUtil.drawTowerByType(tower.getType(),
						tower.getLevel(), g2, tower.getX(), tower.getY(),
						StaticGameInfo.GRID_SIZE);
				int towerLevelX = tower.getX() + 44;
				g2.setColor(Color.yellow);
				for (int j = 0; j < tower.getLevel(); j++) {
					g2.fillRect(towerLevelX, tower.getY() + 6 * j, 6, 4);
				}
			}
		}
	}		
	
	/**
	 * This method is draw a path which from entrance to exit
	 * @param g2
	 */
	private void drawPath(Graphics g2) {
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[i].length; j++) {
				if (path[i][j] == 1) {
					g2.setColor(Color.RED);
					g2.fillRect(j * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X, i* StaticGameInfo.GRID_SIZE
							+ StaticGameInfo.GAMELOCATION_Y, StaticGameInfo.GRID_SIZE,
							StaticGameInfo.GRID_SIZE);
				}
				if (path[i][j] == 2) {
					g2.setColor(Color.BLUE);
					g2.fillRect(j * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X, i* StaticGameInfo.GRID_SIZE
							+ StaticGameInfo.GAMELOCATION_Y, StaticGameInfo.GRID_SIZE,
							StaticGameInfo.GRID_SIZE);
				}
				if (path[i][j] == 3) {
					g2.setColor(Color.BLACK);
					g2.fillRect(j * StaticGameInfo.GRID_SIZE + StaticGameInfo.GAMELOCATION_X, i* StaticGameInfo.GRID_SIZE
							+ StaticGameInfo.GAMELOCATION_Y, StaticGameInfo.GRID_SIZE,
							StaticGameInfo.GRID_SIZE);
				}
			}
		}		
	}

	private void drawGrid(Graphics g2) {
		g2.setColor(Color.BLACK);
		for(int x = 0;x < map_col;x++){
			for(int y = 0;y < map_row;y++){
				g2.drawRect(StaticGameInfo.GRID_SIZE + (x*StaticGameInfo.GRID_SIZE), StaticGameInfo.GRID_SIZE + (y*StaticGameInfo.GRID_SIZE), StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
			}		
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				repaint();
				Thread.sleep(50);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is update a tower
	 * @param tower
	 */
	private void upTower(Tower tower) {
		setMoney( getMoney() - tower.getPrice());
		tower.levelUp();
	}
	
	/**
	 * This method is sell a tower
	 * @param tower
	 */
	private void downTower(Tower tower) {
		setMoney(getMoney() + tower.getPrice() * tower.getLevel());
		tower.setLife(false);
		getTowerList().remove(tower);
	}

	private void setDrawMoney() {
		if (drawMoney) {
			drawMoney = false;
		} else {
			drawMoney = true;
		}
	}
	
	/**
	 * This method will warn players when they want to buy a tower 
	 * do not have enough money 
	 */
	private void noMoneyThread() {
		new Thread() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 4; i++) {
						setDrawMoney();
						Thread.sleep(100);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		boolean canDrawTowerTools = true;
		for (Tower tower : towerList) {
			if (getSquerasX(x) == getSquerasX(tower.getX())
					&& getSquerasY(y) == getSquerasX(tower.getY())) {
				canDrawTowerTools = false;
				if (tower.isEnable()) {
					upX = getSquerasX(x);
					upY = getSquerasY(y);
					focusTower = tower;
				} else {
					upX = -100;
					upY = -100;
				}
				break;
			}
		}
		if (canDrawTowerTools) {
			upX = -100;
			upY = -100;
			up = false;
			broken = false;
		} else {
			if (x > upX && x < upX + 25 && y > upY && y < upY + 25
					&& focusTower.getLevel() < 6) {
				up = true;
			} else {
				up = false;
			}
			if (x > upX + 25 && x < upX + 50 && y > upY && y < upY + 25) {
				broken = true;
			} else {
				broken = false;
			}
		}
		
		if (canDrawTowerTools
				&& x > StaticGameInfo.GAMELOCATION_X
				&& x <  StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE*path[0].length
				&& y >  StaticGameInfo.GAMELOCATION_Y
				&& y <  StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE*path.length
				&& path[(y -  StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE][(x -  StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE] == 0) {
			focusX = getSquerasX(x);
			focusY = getSquerasX(y);
		} else {
			atTools = false;
			for (int i = 0; i < toolsList.size(); i++) {
				Point point = toolsList.get(i);
				if (x > point.getX() && x < point.getX() + StaticGameInfo.GRID_SIZE
						&& y > point.getY() && y < point.getY() + StaticGameInfo.GRID_SIZE) {
					changeTowerType = i;
					focusX = point.getX();
					focusY = point.getY();
					atTools = true;
				}
			}
			if (!atTools) {
				focusX = -100;
				focusY = -100;
				changeTowerType = -1;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (drawTowerTools) {
				drawTowerTools = false;
				towerType = -1;
			}
		} else {
			if (changeTowerType != -1) {
				if (TowerUtil.getPriceByTowerType(changeTowerType) <=getMoney()) {
					drawTowerTools = true;
					towerType = changeTowerType;
				} else {
					noMoneyThread();
				}
			} else if (drawTowerTools && focusX != -100 && focusY != -100) {
				setMoney(getMoney() - TowerUtil.getPriceByTowerType(towerType));
				new Thread() {
					@Override
					public void run() {
						addTower(towerType, focusX, focusY, StaticGameInfo.GRID_SIZE);
						drawTowerTools = false;
						towerType = -1;
					}
				}.start();
			}
			if (up && !drawTowerTools) {
				if (getMoney() >= TowerUtil.getPriceByTowerType(focusTower
						.getType())
						&& focusTower.getLevel() < 6) {
					upTower(focusTower);
					upX = -100;
					upY = -100;
					up = false;
				} else {
					if (focusTower.getLevel() == 6) {

					} else {
						noMoneyThread();
					}
				}
			}
			if (broken && !drawTowerTools) {
				downTower(focusTower);
				upX = -100;
				upY = -100;
				broken = false;
			}
		}
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int[][] getPath() {
		return path;
	}

	public void setPath(int[][] path) {
		this.path = path;
	}

	public int getMap_row() {
		return map_row;
	}

	public void setMap_row(int map_row) {
		this.map_row = map_row;
	}

	public int getMap_col() {
		return map_col;
	}

	public void setMap_col(int map_col) {
		this.map_col = map_col;
	}
	
	public List<Tower> getTowerList() {
		return towerList;
	}

	public void setTowerList(List<Tower> towerList) {
		this.towerList = towerList;
	}
	
	private int getSquerasY(int y) {
		return (y - StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
				+ StaticGameInfo.GRID_SIZE;
	}

	private int getSquerasX(int x) {
		return (x - StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
				+ StaticGameInfo.GRID_SIZE;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}

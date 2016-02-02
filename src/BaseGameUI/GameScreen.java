package BaseGameUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import FighterThread.FighterThread;
import GameData.MapData;
import GameData.StaticGameInfo;
import Model.*;

public class GameScreen extends JPanel implements Runnable, MouseMotionListener, MouseListener{
	
	private int focusX = -100;
	private int focusY = -100;
	private int map_row;
	private int map_col;
	private int entryX;
	private int entryY;
	private int endX;
	private int endY;
	private int money;
	private int round;
	private int changeTowerType;
	private int towerType;
	private int upX;
	private int upY;
	private int click_count = 1;
	private boolean up;
	private boolean broken;
	private boolean atTools;
	private boolean drawTowerTools;
	private boolean drawMoney;	
	private Tower focusTower;
	private List<Point> toolsList;
	private int[][] path = new int[100][100];
	private List<Fighter> fighterList;
	private List<Tower> towerList;
	private JButton start,back;
	private GameScreen gamescreen;
	private MainScreen mainscreen;
	public static String MAP_PATH;
	Thread GST = new Thread(this);
	
	public GameScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		gamescreen = this;
		init();	
		this.addMouseListener(this);
	}
	
	public void init(){
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
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE * 2, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE
				* 8));
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE * 4, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE
				* 8));
		toolsList.add(new Point(StaticGameInfo.GAMELOCATION_X + StaticGameInfo.GRID_SIZE * 6, StaticGameInfo.GAMELOCATION_Y + StaticGameInfo.GRID_SIZE
				* 8));
		this.setLayout(null);
		start = new JButton("Start Game");
		back = new JButton("Back");
		this.add(start);
		this.add(back);
		start.setBounds(700,515,100,30);
		back.setBounds(700,550,100,30);
		start.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				GST.start();
				Thread FT = new Thread(new FighterThread(gamescreen));
				FT.start();			
			}
		});
		back.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				mainscreen.removeAll();
				mainscreen.add(new StartScreen(mainscreen));
				mainscreen.validate();
				mainscreen.repaint();		
			}
		});
		fighterList = new ArrayList<Fighter>();
		towerList = new ArrayList<Tower>();
		this.addMouseMotionListener(this);
		
		try {
			loadMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
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
        			if(new_path[i][j]==2){
        				this.entryX = i;
        				this.entryY = j;
        			}
        			if(new_path[i][j]==3){
        				this.endX = i;
        				this.endY = j;
        			}       			
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
	
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, StaticGameInfo.FRAME_WIDTH, StaticGameInfo.FRAME_HEIGHT);
		drawPath(g);
		drawGrid(g);
		drawFighter(g);
		drawTools(g);
		drawTowers(g);
		drawMoney(g);
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
		drawTowersTools(g);
		drawUpOrDown(g);
		
	}
	
	private void drawUpOrDown(Graphics g2){
		if (upX != -100 && upY != -100 && !drawTowerTools) {
			g2.setColor(Color.white);
			g2.fillRect(upX, upY, 50, 10);
			g2.setColor(Color.orange);
			if (up) {
				g2.fillRect(upX, upY, 25, 10);
			}
			if (broken) {
				g2.fillRect(upX + 25, upY, 25, 10);
			}
			g2.setColor(Color.black);
			g2.drawLine(upX + StaticGameInfo.GRID_SIZE / 2, upY, upX + StaticGameInfo.GRID_SIZE  / 2,
					upY + 10);
			Font font = new Font("Times New Roman", 5, 10);
			g2.setFont(font);
			if (focusTower.getLevel() < 6) {
				g2.drawString("up" + focusTower.getPrice(), upX, upY + 8);
			}
			g2.drawString(" down", upX + 25, upY + 8);
			g2.setColor(Color.gray);
			g2.fillRect(upX, upY+10, 50, 30);
			g2.setColor(Color.black);
			g2.drawString("level: " + focusTower.getLevel(), upX, upY + 17);
			g2.drawString("power: " + focusTower.getPower(), upX, upY + 25);
			g2.drawString("refund: " + "100%", upX, upY + 33);
			g2.drawString("speical: " + focusTower.getSpecial_effects(), upX, upY + 42);
			}
	}
	
	private void drawMoney(Graphics g2){
		if (drawMoney) {
			Font font = new Font("TimesRoman", 30, 30);
			g2.setFont(font);
			g2.setColor(Color.black);
			g2.drawString("$" + getMoney(), StaticGameInfo.GAMELOCATION_X + 12 * StaticGameInfo.GRID_SIZE, StaticGameInfo.GAMELOCATION_Y
					+ 9 * StaticGameInfo.GRID_SIZE - 10);
		}
		Font font = new Font("TimesRoman", 30, 30);
		g2.setColor(Color.black);
		g2.setFont(font);
		g2.drawString("round" + getRound(), StaticGameInfo.GAMELOCATION_X + 9 * StaticGameInfo.GRID_SIZE - 25,
				StaticGameInfo.GAMELOCATION_Y + 9 * StaticGameInfo.GRID_SIZE - 10);
	}

	
	private void drawTowersTools(Graphics g2){
		if (drawTowerTools) {
			DrawTowerUtil.drawTowerByType(towerType, 1, g2, focusX, focusY,
					StaticGameInfo.GRID_SIZE);
			g2.setColor(Color.green);
			g2.drawArc(focusX-50, focusY-50, 150, 150, 0, 360);
		}
		
	}
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
	private void drawTowers(Graphics g2){
		for (int i = 0; i < towerList.size(); i++) {
			Tower tower = towerList.get(i);
			if (tower != null) {
				DrawTowerUtil.drawTowerByType(tower.getType(),
						tower.getLevel(), g2, tower.getX(), tower.getY(),
						StaticGameInfo.GRID_SIZE);
				int towerLevelX = tower.getX() + 42;
				g2.setColor(Color.yellow);
				for (int j = 0; j < tower.getLevel(); j++) {
					g2.fillRect(towerLevelX, tower.getY() + 4 * j, 6, 3);
				}
								//if (!tower.isEnable()) {
				//	DrawTowerUtil.drawTowerLife(g2, tower);
			//	}
			
			}
				g2.setColor(Color.green);
				g2.drawArc(tower.getX()-(25+25*click_count), tower.getY()-(25+25*click_count), tower.getRange(), tower.getRange(), 0, 360);

			}
		}		
	
	
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
	
	private void drawFighter(Graphics g2){
		List<Fighter> fighterList = this.getFighterList();
		for(int i=0;i<fighterList.size();i++){
			fighterList.get(i).draw(g2);
		}
	}

	@Override
	public void run() {
		int count =1;
		while(true){
			try {
				if(count%10 == 0){
					fighterList.add(new Ball(this.entryX,this.entryY,this));
					count = 1;
				}else{
					repaint();
					Thread.sleep(50);
					count++;
				}		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void upTower(final Tower tower) {
		setMoney( getMoney() - tower.getPrice());
		tower.levelUp();
	}

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
			if (x > upX && x < upX + 25 && y > upY && y < upY + 10
					&& focusTower.getLevel() < 6) {
				up = true;
			} else {
				up = false;
			}
			if (x > upX + 25 && x < upX + 50 && y > upY && y < upY + 10) {
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
				&& path[(y -  StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE][(x -  StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE] != 1) {
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
	/*	if (x > StaticGameInfo.GAMELOCATION_X && x < StaticGameInfo.GAMELOCATION_X + map_col*StaticGameInfo.GRID_SIZE && y > StaticGameInfo.GAMELOCATION_Y
				&& y < StaticGameInfo.GAMELOCATION_Y + map_row*StaticGameInfo.GRID_SIZE) {
			focusX = (x - StaticGameInfo.GAMELOCATION_X) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
					+ StaticGameInfo.GRID_SIZE;
			focusY = (y - StaticGameInfo.GAMELOCATION_Y) / StaticGameInfo.GRID_SIZE * StaticGameInfo.GRID_SIZE
					+ StaticGameInfo.GRID_SIZE;
		} else {
			focusX = -100;
			focusY = -100;
		}
	*/	
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
						System.out.println("focusx "+focusX+"focusY"+focusY);
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
					click_count++;
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
	public int[][] getPath() {
		return path;
	}

	public void setPath(int[][] path) {
		this.path = path;
	}

	public List<Fighter> getFighterList() {
		return fighterList;
	}

	public void setFighterList(List<Fighter> fighterList) {
		this.fighterList = fighterList;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
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
}

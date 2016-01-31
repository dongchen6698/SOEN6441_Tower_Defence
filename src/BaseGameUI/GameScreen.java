package BaseGameUI;
import java.awt.Color;
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
	private int changeTowerType;
	private int towerType;
	private boolean atTools;
	private boolean drawTowerTools;
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
		atTools = false;
		drawTowerTools = false;
		changeTowerType = -1;
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
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.GRID_SIZE, StaticGameInfo.GRID_SIZE);
		drawTowersTools(g);
		
	}
	private void drawTowersTools(Graphics g2){
		if (drawTowerTools) {
			DrawTowerUtil.drawTowerByType(towerType, 1, g2, focusX, focusY,
					StaticGameInfo.GRID_SIZE);
		}
	}
	private void drawTools(Graphics g2){
		for (int i = 0; i < toolsList.size(); i++) {
			DrawTowerUtil.drawTowerByType(i, 1, g2, toolsList.get(i).getX(),
					toolsList.get(i).getY(), StaticGameInfo.GRID_SIZE);
		}
	}
	private void drawTowers(Graphics g2){
		for (int i = 0; i < towerList.size(); i++) {
			Tower tower = towerList.get(i);
			DrawTowerUtil.drawTowerByType(tower.getType(), tower.getLevel(),
					g2, tower.getX(), tower.getY(), StaticGameInfo.GRID_SIZE);
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
				break;
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

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (drawTowerTools) {
				drawTowerTools = false;
				towerType = -1;
			}
		} else {
			if (changeTowerType != -1) {
				drawTowerTools = true;
				towerType = changeTowerType;
			} else if (drawTowerTools && focusX != -100 && focusY != -100) {
				addTower(towerType, focusX, focusY, StaticGameInfo.GRID_SIZE);
				drawTowerTools = false;
				towerType = -1;
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

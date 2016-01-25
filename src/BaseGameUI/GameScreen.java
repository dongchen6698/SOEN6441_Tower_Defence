package BaseGameUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import FighterThread.FighterThread;
import GameData.MapData;
import GameData.StaticGameInfo;
import Model.*;


public class GameScreen extends JPanel implements Runnable, MouseMotionListener{
	public MainScreen mainscreen;
	Thread gameScreenThread = new Thread(this);
	private int focusX;
	private int focusY;
	public static String map_path;
	private int map_row;
	private int map_col;
	private int entryX;
	private int entryY;
	private int endX;
	private int endY;
	private int[][] path = new int[100][100];
	private List<Fighter> fighterList;
	
	public GameScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		init();	
	}
	
	public void init(){
		fighterList = new ArrayList<Fighter>();
		this.addMouseMotionListener(this);
		try {
			loadMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gameScreenThread.start();
		FighterThread fighterthread = new FighterThread(this);
		Thread FT = new Thread(fighterthread);
		FT.start();
	
	}
	
	public void loadMap() throws FileNotFoundException{
		MapData mapinfo = new MapData(map_path);
		map_row = mapinfo.getGridRow();
		map_col = mapinfo.getGridCol();
		InputStream in = new FileInputStream(map_path); 
        Scanner scanner = new Scanner(in);
        while(scanner.hasNext()){   	
        	for(int i=0;i < mapinfo.getGridRow();i++){
        		for(int j=0;j < mapinfo.getGridCol();j++){
        			path[i][j] = scanner.nextInt();
        			if(path[i][j]==2){
        				this.entryX = i;
        				this.entryY = j;
        			}
        			if(path[i][j]==3){
        				this.endX = i;
        				this.endY = j;
        			}
        			
        		}
        	}
        }
       try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, StaticGameInfo.frameWidth, StaticGameInfo.frameHeight);
		drawPath(g);
		drawGrid(g);
		drawFighter(g);
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}

	private void drawPath(Graphics g2) {

		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[i].length; j++) {
				if (path[i][j] == 1) {
					g2.setColor(Color.RED);
					g2.fillRect(j * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX, i* StaticGameInfo.gridSize
							+ StaticGameInfo.gameLocationY, StaticGameInfo.gridSize,
							StaticGameInfo.gridSize);
				}
				if (path[i][j] == 2) {
					g2.setColor(Color.BLUE);
					g2.fillRect(j * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX, i* StaticGameInfo.gridSize
							+ StaticGameInfo.gameLocationY, StaticGameInfo.gridSize,
							StaticGameInfo.gridSize);
				}
				if (path[i][j] == 3) {
					g2.setColor(Color.BLACK);
					g2.fillRect(j * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX, i* StaticGameInfo.gridSize
							+ StaticGameInfo.gameLocationY, StaticGameInfo.gridSize,
							StaticGameInfo.gridSize);
				}
			}
		}		
	}

	private void drawGrid(Graphics g2) {
		g2.setColor(Color.BLACK);
		for(int x = 0;x < map_col;x++){
			for(int y = 0;y < map_row;y++){
				g2.drawRect(StaticGameInfo.gridSize + (x*StaticGameInfo.gridSize), StaticGameInfo.gridSize + (y*StaticGameInfo.gridSize), StaticGameInfo.gridSize, StaticGameInfo.gridSize);
			}		
		}
	}
	
	public void drawFighter(Graphics g2){
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
					gameScreenThread.sleep(50);
					count++;
				}		
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getFuturePoint(Fighter f) {
		int x = (f.getX() - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize;
		int y = (f.getY() - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize;
		int dir = f.getDirection();
		if (dir == 1) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 7 && y < 12) {
				if (path[x - 1][y] == 1) {
					f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(1);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 7 && y - 1 < 12) {
				if (path[x][y - 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 7 && y + 1 < 12) {
				if (path[x][y + 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(4);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(1);
			// }
		} else if (dir == 2) {
			if (x + 1 >= 0 && y >= 0 && x + 1 < 7 && y < 12) {
				if (path[x + 1][y] == 1) {
					f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 7 && y - 1 < 12) {
				if (path[x][y - 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(3);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 7 && y + 1 < 12) {
				if (path[x][y + 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(4);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(2);
			// }
		} else if (dir == 3) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 7 && y < 12) {
				if (path[x - 1][y] == 1) {
					f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < 7 && y < 12) {
				if (path[x + 1][y] == 1) {
					f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y - 1 >= 0 && x < 7 && y - 1 < 12) {
				if (path[x][y - 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(3);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY((y - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(3);
			// }
		} else if (dir == 4) {
			if (x - 1 >= 0 && y >= 0 && x - 1 < 7 && y < 12) {
				if (path[x - 1][y] == 1) {
					f.setFutureX((x - 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(1);
				}
			}
			if (x + 1 >= 0 && y >= 0 && x + 1 < 7 && y < 12) {
				if (path[x + 1][y] == 1) {
					f.setFutureX((x + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY(y * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(2);
				}
			}
			if (x >= 0 && y + 1 >= 0 && x < 7 && y + 1 < 12) {
				if (path[x][y + 1] == 1) {
					f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
					f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
					f.setDirection(4);
				}
			}
			// if (f.getX() == f.getFutureX() && f.getY() == f.getFutureY()) {
			// f.setFutureX(x * StaticGameInfo.gridSize + StaticGameInfo.gameLocationX);
			// f.setFutureY((y + 1) * StaticGameInfo.gridSize + StaticGameInfo.gameLocationY);
			// f.setDirection(4);
			// }
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
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + 14*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + 7*StaticGameInfo.gridSize) {
			focusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			focusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
		} else {
			focusX = -100;
			focusY = -100;
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
	
}

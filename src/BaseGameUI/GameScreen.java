package BaseGameUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.*;

import GameData.StaticGameInfo;


public class GameScreen extends JPanel implements Runnable, MouseMotionListener{
	public MainScreen mainscreen;
	Thread baseScreenThread = new Thread(this);
	private int focusX;
	private int focusY;
	public static String map_path;
	private int map_row;
	private int map_col;
	private int[][] path = new int[100][100];
	public GameScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		init();	
	}
	
	public void init(){
		this.addMouseMotionListener(this);
		try {
			loadMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		baseScreenThread.start();
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
		
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}

	private void drawPath(Graphics g2) {
		g2.setColor(Color.red);
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[i].length; j++) {
				if (path[i][j] == 1) {
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

	@Override
	public void run() {
		while(true){
			repaint();
			try {
				baseScreenThread.sleep(5);
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
	
}

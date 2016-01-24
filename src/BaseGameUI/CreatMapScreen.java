package BaseGameUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GameData.StaticGameInfo;

public class CreatMapScreen extends JPanel implements Runnable, MouseMotionListener, MouseListener{
	private int maprow;
	private int mapcol;
	private int focusX = -100;
	private int focusY = -100;
	private int clickfocusX = -100;
	private int clickfocusY = -100;
	private int[][] creatmappath;
	public int clickinfo;
	int count = 1 ;
	public MainScreen mainscreen;
	
	Thread creatmapscreen = new Thread(this);
	
	public CreatMapScreen(MainScreen mainscreen){
		this.mainscreen = mainscreen;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		creatmapscreen.start();

	}
	
	public void paintComponent(Graphics g) {
		if(count == 1){
		g.clearRect(0, 0, StaticGameInfo.frameWidth, StaticGameInfo.frameHeight);
		count++;
		}
		drawGrid(g);
		switch(clickinfo){
		case 1: clickPath(g);break;
		case 2: clickEntryPoint(g);break;
		case 3: clickExitPoint(g);break;
		}
		g.setColor(Color.green);
		g.drawRect(focusX, focusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
	}
	
	public void drawGrid(Graphics g2) {
		g2.setColor(Color.BLACK);
		for(int x = 0;x < mapcol;x++){
			for(int y = 0;y < maprow;y++){
				g2.drawRect(StaticGameInfo.gridSize + (x*StaticGameInfo.gridSize), StaticGameInfo.gridSize + (y*StaticGameInfo.gridSize), StaticGameInfo.gridSize, StaticGameInfo.gridSize);
			}		
		}
	}
	
	public void saveMap() throws IOException{
		System.out.println("start savefile");
		
		for(int i=0;i<creatmappath.length;i++){
			System.out.print("\n");
			for(int j=0;j<creatmappath[i].length;j++){
			System.out.print(creatmappath[i][j]+" ");
			}
		}
		
		File file = new File("Maps/savefile");
		FileWriter out = new FileWriter(file);
		for(int i=0;i<creatmappath.length;i++){
		for(int j=0;j<creatmappath[i].length;j++){
				out.write(creatmappath[i][j]+" ");
			}
			out.write("\n");
		}
		System.out.println("\n"+"over save");
		out.close();
		
		mainscreen.removeAll();
		mainscreen.add(new StartScreen(mainscreen));
		mainscreen.validate();
		mainscreen.repaint();
	}
	
	public void clickPath(Graphics g2){
		g2.setColor(Color.RED);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
	
	public void clickEntryPoint(Graphics g2){
		g2.setColor(Color.BLUE);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
	
	public void clickExitPoint(Graphics g2){
		g2.setColor(Color.BLACK);
		g2.fillRect(clickfocusX, clickfocusY, StaticGameInfo.gridSize, StaticGameInfo.gridSize);
		clickinfo = 0;
	}
	

	@Override
	public void run() {
		while(true){
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}

	public int getMaprow() {
		return maprow;
	}

	public void setMaprow(int maprow) {
		this.maprow = maprow;
	}

	public int getMapcol() {
		return mapcol;
	}
	
	public void setMapcol(int mapcol) {
		this.mapcol = mapcol;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + mapcol*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + maprow*StaticGameInfo.gridSize) {
			focusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			focusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
		} else {
			focusX = -100;
			focusY = -100;
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > StaticGameInfo.gameLocationX && x < StaticGameInfo.gameLocationX + mapcol*StaticGameInfo.gridSize && y > StaticGameInfo.gameLocationY
				&& y < StaticGameInfo.gameLocationY + maprow*StaticGameInfo.gridSize) {
			clickfocusX = (x - StaticGameInfo.gameLocationX) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			clickfocusY = (y - StaticGameInfo.gameLocationY) / StaticGameInfo.gridSize * StaticGameInfo.gridSize
					+ StaticGameInfo.gridSize;
			
			Object[] clicklist = {"Entry Point","Path","Exit Point"};
			System.out.println(clickfocusX+" "+clickfocusY+" ");
			String content = (String)JOptionPane.showInputDialog(null,"Which point you want to draw?\n","Draw point",JOptionPane.PLAIN_MESSAGE,null,clicklist,"Path");
			if(content == "Path"){
				clickinfo = 1;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 1;
			}else if(content == "Entry Point"){
				clickinfo = 2;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 2;
			}else if(content == "Exit Point"){
				clickinfo = 3;
				creatmappath[(clickfocusY/50)-1][(clickfocusX/50)-1] = 3;
			}
			
		} else {
			clickfocusX = -100;
			clickfocusY = -100;
			try {
				saveMap();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			for(int i=0;i<creatmappath.length;i++){
//					System.out.println("\n");
//				for(int j=0;j<creatmappath[i].length;j++){
//					System.out.print(creatmappath[i][j]+" ");
//				}
//			}
			
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

	public int[][] getCreatmappath() {
		return creatmappath;
	}

	public void setCreatmappath(int[][] creatmappath) {
		this.creatmappath = creatmappath;
	}



	
	

}
